package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.CloneCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.SetCommand;
import duke.command.ShowCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Parser class handles all actions from users' input.
 */
public class Parser {
    /** The spliter for the date time. */
    public static final String SPLITER = ",";

    /** The date time format. */
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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
        case "edit":
            return Action.EDIT;
        case "clone":
            return Action.CLONE;
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
            assert array.length == 3 : "Parse todo document fails";
            return new Todo(array[2], parseIsDone(array[1]));
        case "E":
            assert array.length == 4 : "Parse event document fails";
            return new Event(array[2], parseIsDone(array[1]), parseDateTime(array[3]));
        case "D":
            assert array.length == 4 : "Parse deadline document fails";
            return new Deadline(array[2], parseIsDone(array[1]), parseDateTime(array[3]));
        default:
            String errorMessage = "Error in saved tasks document: " + input;
            throw new DukeException(errorMessage);
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
            LocalDateTime ldt = LocalDateTime.parse(input, INPUT_DATE_TIME_FORMATTER);
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
            return new AddCommand(Action.EVENT, rest);
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
        case EDIT: {
            try {
                int indexOfSpace = rest.indexOf(" ");
                String num = rest.substring(0, indexOfSpace);
                String info = rest.substring(indexOfSpace + 1);
                int taskNumber = Integer.parseInt(num);
                return new EditCommand(Action.EDIT, taskNumber - 1, info);
            } catch (NumberFormatException e) {
                throw new DukeException("A number must be given to specified the task.");
            }
        }
        case CLONE: {
            try {
                int taskNumber = Integer.parseInt(rest);
                return new CloneCommand(taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("A number must be given to specified the task.");
            }
        }
        case UNKNOWN:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        default:
            return null;
        }
    }

    /**
     * Returns the edit command info based on the given input.
     *
     * @param input The given input.
     * @return The edit command info based on the given input.
     */
    public static String[] parseEditInfo(String input) {
        String[] arr;
        if (input.contains("/by")) {
            arr = input.indexOf("/by") != 0 ? input.split(" /by ") : input.split("/by ");
        } else if (input.contains("/at")) {
            arr = input.indexOf("/at") != 0 ? input.split(" /at ") : input.split("/at ");
        } else {
            arr = new String[]{input};
        }
        return arr;
    }
}
