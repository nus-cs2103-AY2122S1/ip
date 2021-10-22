package captain.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import captain.DukeException;
import captain.DukeException.MissingDescriptionException;
import captain.command.AddCommand;
import captain.task.Deadline;
import captain.task.Event;
import captain.task.Task;
import captain.task.Todo;

public class AddCommandParser implements Parser<AddCommand> {
    private static final String PREFIX_BY = "/by";
    private static final String PREFIX_AT = "/at";
    private static final Pattern TASK_FORMAT = Pattern.compile("(?<task>.*)\\s(?<specifier>[\\/][a-z]+)\\s(?<date>.+)");

    @Override
    public AddCommand<Task> parse(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new MissingDescriptionException();
        }

        final Matcher matcher = TASK_FORMAT.matcher(args);

        if (matcher.matches()) {
            String task = matcher.group("task");
            String specifier = matcher.group("specifier");
            LocalDate date = formatDate(matcher.group("date"));

            switch (specifier) {
            case PREFIX_BY:
                return new AddCommand<>(new Deadline(task, date));
            case PREFIX_AT:
                return new AddCommand<>(new Event(task, date));
            default:
                throw new DukeException.InvalidInputException();
            }
        }
        return new AddCommand<>(new Todo(args));
    }


    /**
     * Converts the String representation of a date to a LocalDate type.
     * @param date The date input by the user.
     * @return A LocalDate object representing the date.
     * @throws DukeException.InvalidDateException Throws an InvalidDateException
     * if the input date is in the wrong format.
     */
    public static LocalDate formatDate(String date) throws DukeException.InvalidDateException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException.InvalidDateException();
        }
    }
}
