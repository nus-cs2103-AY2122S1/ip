package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class handles all actions from users' input.
 */
public class Parser {
    /** The Action enum enumerates all possible actions. */
    public enum Action {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    /** The spliter for the date time. */
    public static final String SPLITER = ",";

    /** The date time format. */
    public static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Returns an action based on the given input string.
     *
     * @param input The given input string.
     * @return An action based on the given string.
     */
    public static Action getAction(String input) {
        switch (input) {
        case "bye":
            return Action.BYE;
        case "list":
            return Action.LIST;
        case "done":
            return Action.DONE;
        case "todo":
            return Action.TODO;
        case "deadline":
            return Action.DEADLINE;
        case "event":
            return Action.EVENT;
        case "delete":
            return Action.DELETE;
        default:
            return Action.UNKNOWN;
        }
    }

    /**
     * Returns the boolean value based on the input string.
     *
     * @param input String 0 or 1.
     * @return False if the string is 0, returns true otherwise.
     */
    public static boolean parseIsDone(String input) {
        if (input.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns a task based on the given input string from .txt file.
     *
     * @param input The given input string from .txt file.
     * @return A task based on the given input string.
     * @throws DukeException If parse fails.
     */
    public static Task parseDocument(String input) {
        String[] array = input.split(SPLITER);
        switch (array[0]) {
        case "T":
            return new Todo(array[2], parseIsDone(array[1]));
        case "E":
            return new Event(array[2], parseIsDone(array[1]), parseDateTime(array[3]));
        case "D":
            return new Deadline(array[2], parseIsDone(array[1]), parseDateTime(array[3]));
        default:
            throw new DukeException("Error in saved tasks document");
        }
    }

    /**
     * Returns LocalDateTime instance based on the input string.
     *
     * @param input The given input string whose format follows dd/MM/yyyy HH:mm.
     * @return A LocalDateTime instance based on the input string.
     */
    public static LocalDateTime parseDateTime(String input) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(input, inputDateTimeFormatter);
            return ldt;
        } catch (DateTimeParseException e) {
            throw new DukeException("time should be in the format: DD/MM/YYYY HH:MM");
        }
    }
}
