package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.*;
import task.TaskList;

/**
 * Parses any command that requires inputs.
 */
public class DukeParser {

    /**
     * Patterns for the Parser to look out for in the input.
     */
    private static final Pattern LIST_PATTERN = Pattern.compile("list( .+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern DONE_PATTERN = Pattern.compile("done (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_PATTERN = Pattern.compile("delete (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern TODO_PATTERN = Pattern.compile("todo (.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN = Pattern.compile(
            "deadline (.+) /by (\\d{1,2}/\\d{1,2}/\\d{4}+)( \\d{4}+)?",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN = Pattern.compile(
            "event (.+) /at (\\d{1,2}/\\d{1,2}/\\d{4}+)( \\d{4}+)?",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern SORT_PATTERN = Pattern.compile("sort( .+)?", Pattern.CASE_INSENSITIVE);

    private final TaskList taskList;

    /**
     * Constructor; Instantiates the task list to be edited.
     *
     * @param tasks Task list to edit using this Parser object.
     */
    public DukeParser(TaskList tasks) {
        this.taskList = tasks;
    }

    /**
     * Matches input with any regex, and passes the matching command.
     *
     * @param input String input from the Listener given by the User.
     */
    public Command parseInput(String input) {
        assert input != null: "Input to parse cannot be null";

        if (input.equals("gubbai")) {
            return new CommandExit();
        } else if (input.equals("help")) {
            return new CommandHelp();
        }

        final Matcher checkList = LIST_PATTERN.matcher(input);
        final Matcher checkDone = DONE_PATTERN.matcher(input);
        final Matcher checkDelete = DELETE_PATTERN.matcher(input);
        final Matcher checkTodo = TODO_PATTERN.matcher(input);
        final Matcher checkDeadline = DEADLINE_PATTERN.matcher(input);
        final Matcher checkEvent = EVENT_PATTERN.matcher(input);
        final Matcher checkSort = SORT_PATTERN.matcher(input);

        // Check if matcher found any matches and returns relevant command
        if (checkList.matches()) {
            return new CommandList(taskList, checkList.group(1));
        } else if (checkDone.matches()) {
            return new CommandDone(taskList, Integer.parseInt(checkDone.group(1)));
        } else if (checkDelete.matches()) {
            return new CommandDelete(taskList, Integer.parseInt(checkDelete.group(1)));
        } else if (checkTodo.matches()) {
            return new CommandAddTodo(taskList, checkTodo.group(1));
        } else if (checkDeadline.matches()) {
            return new CommandAddDeadline(taskList, new String[]{checkDeadline.group(1),
                    checkDeadline.group(2),
                    checkDeadline.group(3)
            });
        } else if (checkEvent.matches()) {
            return new CommandAddEvent(taskList, new String[]{checkEvent.group(1),
                    checkEvent.group(2),
                    checkEvent.group(3)
            });
        } else if (checkSort.matches()) {
            return new CommandSort(taskList, checkSort.group(1));
        } else {
            return new CommandInvalid(input);
        }
    }

    /**
     * Takes in a String date and returns its corresponding LocalDate object.
     *
     * @param date String date in format DD/MM/YYYY, with 1-2 digits from Day and Month.
     * @return LocalDate object with the corresponding day, month and year.
     * @throws DateTimeParseException Thrown if date passed is an invalid one.
     */
    public static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }


}
