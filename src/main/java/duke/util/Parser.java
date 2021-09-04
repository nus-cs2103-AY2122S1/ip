package duke.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidTimeException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeMissingDateTimeException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeMissingIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parser class deals with making sense of the user command.
 *
 * @author Chng Zi Hao
 */
public class Parser {
    private static String extractInfo(String input, Instruction instruction) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length < 2 || info[1].equals("")) {
            throw new DukeMissingDescriptionException(instruction);
        }
        return info[1];
    }

    private static int extractIndex(String input, Instruction instruction) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length < 2 || info[1].equals("")) {
            throw new DukeMissingIndexException(instruction);
        }
        return Integer.parseInt(info[1]) - 1;
    }

    private static String[] extractDeadline(String info) throws DukeException {
        if (!info.contains("/by")) {
            throw new DukeMissingArgumentException("/by");
        }

        String[] description = info.split(" /by ", 2);

        if (description.length < 2 || description[1].equals("")) {
            throw new DukeMissingDateTimeException();
        }
        return description;
    }

    private static String[] extractEvent(String info) throws DukeException {
        if (!info.contains("/at")) {
            throw new DukeMissingArgumentException("/at");
        }

        String[] description = info.split(" /at ", 2);

        if (description.length < 2 || description[1].equals("")) {
            throw new DukeMissingDateTimeException();
        }
        return description;
    }

    /**
     * Returns a LocalDate object from date string.
     *
     * @param date String representation of date.
     * @return LocalDate object representing the date.
     * @throws DukeInvalidDateException If date input does not match regex or is invalid date.
     */
    public static LocalDate parseDate(String date) throws DukeInvalidDateException {
        try {
            return getLocalDate(date);
        } catch (DateTimeException e) {
            throw new DukeInvalidDateException();
        }
    }

    private static LocalDate getLocalDate(String date) throws DukeInvalidDateException {
        // Reuse regex from
        // https://www.javacodeexamples.com/java-regular-expression-validate-date-example-regex/1504
        String[] dateSplit;
        if (date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])")) {
            // for yyyy-mm-dd
            return LocalDate.parse(date);
        } else if (date.matches("(0[1-9]|[12][0-9]|[3][01])-(0[1-9]|1[012])-\\d{4}")) {
            // for dd-mm-yyyy
            dateSplit = date.split("-", 3);
            return LocalDate.parse(String.format("%s-%s-%s", dateSplit[2], dateSplit[1], dateSplit[0]));
        } else if (date.matches("(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}")) {
            // for dd/mm/yyyy
            dateSplit = date.split("/", 3);
            return LocalDate.parse(String.format("%s-%s-%s", dateSplit[2], dateSplit[1], dateSplit[0]));
        } else {
            throw new DukeInvalidDateException();
        }
    }

    /**
     * Returns a LocalTime object from time string.
     *
     * @param time String representation of time.
     * @return LocalTime object representing the time.
     * @throws DukeInvalidTimeException If time input does not match regex or is invalid time.
     */
    public static LocalTime parseTime(String time) throws DukeInvalidTimeException {
        try {
            return getLocalTime(time);
        } catch (DateTimeException e) {
            throw new DukeInvalidTimeException();
        }
    }

    private static LocalTime getLocalTime(String time) throws DukeInvalidTimeException {
        // Reuse regex from
        // https://www.geeksforgeeks.org/how-to-validate-time-in-24-hour-format-using-regular-expression/
        if (time.length() == 4) {
            String t = String.format("%s:%s", time.substring(0, 2), time.substring(2, 4));
            if (t.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                return LocalTime.parse(t);
            } else {
                throw new DateTimeException("Invalid Time");
            }
        } else if (time.length() == 5 && time.split(":", 2).length == 2) {
            return LocalTime.parse(time);
        } else {
            throw new DukeInvalidTimeException();
        }
    }


    /**
     * Splits string with date and time combined to two separate string containing time and date
     * for Deadline task.
     *
     * @param dateTime String representation of DateTime
     * @return Array of String with date and time separated.
     * @throws DukeMissingDateTimeException If input does not contain time or date.
     */
    public static String[] splitDeadlineDateTime(String dateTime) throws DukeMissingDateTimeException {
        String[] splitDateTime = dateTime.split(" ", 2);
        if (splitDateTime.length != 2) {
            throw new DukeMissingDateTimeException();
        }
        return splitDateTime;
    }

    /**
     * Splits string with date and time combined to two separate string containing time and date
     * for Event task.
     *
     * @param dateTime String representation of DateTime
     * @return Array of String with date and time separated.
     * @throws DukeMissingDateTimeException If input does not contain time or date.
     */
    public static String[] splitEventDateTime(String dateTime) throws DukeMissingDateTimeException {
        String[] splitDateTime = dateTime.split(" ", 2);

        if (splitDateTime.length != 2) {
            throw new DukeMissingDateTimeException();
        }

        String[] splitStartEnd = splitDateTime[1].split("-", 2);

        if (splitStartEnd.length != 2) {
            throw new DukeMissingDateTimeException();
        }
        return new String[]{splitDateTime[0], splitStartEnd[0], splitStartEnd[1]};
    }

    /**
     * Interprets user input and returns command to be executed.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws DukeException If any exception occurs when interpreting user input.
     */
    public Command parse(String input) throws DukeException {
        String extractedInstruction = input.split(" ")[0];
        Instruction instruction = Instruction.getValueOfLabel(extractedInstruction);
        switch (instruction) {
        case LIST:
            return new ListCommand();
        case DONE:
            int doneIndex = extractIndex(input, Instruction.DONE);
            return new DoneCommand(doneIndex);
        case HELP:
            return new HelpCommand();
        case TODO:
            String toDoInfo = extractInfo(input, Instruction.TODO);
            ToDo toDo = new ToDo(toDoInfo);
            return new AddCommand(toDo);
        case DEADLINE:
            String[] deadlineInfo = extractDeadline(extractInfo(input, Instruction.DEADLINE));
            String[] deadlineDateTime = splitDeadlineDateTime(deadlineInfo[1]);
            Deadline deadline = new Deadline(deadlineInfo[0], parseDate(deadlineDateTime[0]),
                    parseTime(deadlineDateTime[1]));
            return new AddCommand(deadline);
        case EVENT:
            String[] eventInfo = extractEvent(extractInfo(input, Instruction.EVENT));
            String[] eventDateTime = splitEventDateTime(eventInfo[1]);
            Event event = new Event(eventInfo[0], parseDate(eventDateTime[0]), parseTime(eventDateTime[1]),
                    parseTime(eventDateTime[2]));
            return new AddCommand(event);
        case DELETE:
            int deleteIndex = extractIndex(input, Instruction.DELETE);
            return new DeleteCommand(deleteIndex);
        case FILTER:
            String filterDate = extractInfo(input, Instruction.FILTER);
            return new FilterCommand(parseDate(filterDate));
        case FIND:
            return new FindCommand(extractInfo(input, Instruction.FIND));
        case BYE:
            return new ExitCommand();
        default:
            assert !extractedInstruction.matches("todo|event|deadline|list|help|find|filter|done|delete|bye");
            return new InvalidCommand();
        }
    }
}
