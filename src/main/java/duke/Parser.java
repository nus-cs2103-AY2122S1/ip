package duke;

import duke.command.*;
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
        case "find":
            return Action.FIND;
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

    /**
     * Returns a command based on the given input.
     *
     * @param input The given input.
     * @return A command.
     * @throws DukeException When parse exception happens.
     */
    public static Command parseCommand(String input) {
        int firstWordIndex = input.indexOf(" ");
        String actionText = firstWordIndex == -1 ? input : input.substring(0, firstWordIndex);
        Action action = Parser.getAction(actionText);
        String rest = input.substring(firstWordIndex + 1);
        switch (action) {
        case BYE: {
            return new ExitCommand(Action.BYE);
        }
        case LIST: {
            return new ShowCommand(Action.LIST);
        }
        case DONE: {
            try {
                int taskNumber = Integer.parseInt(rest);
                return new SetCommand(Action.DONE, taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("A number must be given to specified the task.");
            }
        }
        case TODO: {
            if (firstWordIndex == -1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new AddCommand(Action.TODO, rest);
        }
        case DEADLINE: {
            if (firstWordIndex == -1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            return new AddCommand(Action.DEADLINE, rest);
        }
        case EVENT: {
            if (firstWordIndex == -1) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            return new AddCommand(Action.DEADLINE, rest);
        }
        case DELETE: {
            try {
                int taskNumber = Integer.parseInt(rest);
                return new DeleteCommand(Action.DELETE, taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("A number must be given to specified the task.");
            }
        }
        case FIND: {
            return new FindCommand(Action.FIND, rest);
        }
        case UNKNOWN:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        default:
            return null;
        }
    }
}
