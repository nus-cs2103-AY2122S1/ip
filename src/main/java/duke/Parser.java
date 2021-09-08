package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class to parse user commands.
 */
public class Parser {
    private static final String UNKNOWN_COMMAND = "☹︎wut☁︎☻ unknown command";
    private static final String INVALID_DATETIME_FORMAT =
            "Invalid date and time input, indicate date in yyyy-MM-dd HH:mm format.";
    private static final String TOO_MANY_ARGUMENTS = "Too many arguments for this command.";


    /**
     * The method to parse an input line by the user
     *
     * @param input the String line to be parsed
     * @return the command to be executed
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] x = input.split(" ");
        String cmd = x[0];
        if (x.length == 1) {
            if (cmd.equals("bye")) {
                return new ExitCommand();
            } else if (cmd.equals("list")) {
                return new ListCommand();
            } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                missingTaskName(cmd);
            } else if (cmd.equals("done") || cmd.equals("delete")) {
                throw new DukeException("Indicate a task number beside the command ☻");
            } else if (cmd.equals("find")) {
                throw new DukeException("Enter a keyword beside the command ☻");
            } else {
                throw new DukeException(UNKNOWN_COMMAND);
            }
        } else {
            if (cmd.equals("done")) {
                return parseDoneCommand(x);
            } else if (cmd.equals("delete")) {
                return parseDeleteCommand(x);
            } else if (cmd.equals("find")) {
                return new FindCommand(getKeyword(input));
            } else if (cmd.equals("todo")) {
                return addToDo(input);
            } else if (cmd.equals("deadline")) {
                return parseDeadlineCommand(input);
            } else if (cmd.equals("event")) {
                return parseEventCommand(input);
            } else {
                throw new DukeException(UNKNOWN_COMMAND);
            }
        }
        throw new DukeException(UNKNOWN_COMMAND);
    }

    /**
     * The method to throw an exception user does not fill in task name
     *
     * @param cmd the type of command
     * @throws DukeException
     */
    public static void missingTaskName(String cmd) throws DukeException {
        String str = String.format("☹ OOPS!!! The description of a %s cannot be empty.", cmd);
        throw new DukeException(str);
    }

    /**
     * The method to return an AddCommand with a task
     *
     * @param input String to be parsed to get the Task
     * @return the AddCommand with the Task to be added
     */
    public static Command addToDo(String input) {
        String name = input.substring(input.indexOf(" ") + 1).strip();
        ToDo t = new ToDo(name);
        return new AddCommand(t);
    }

    /**
     * The method to return an AddCommand with a task
     *
     * @param input String to be parsed to get the Task
     * @return the AddCommand with the Task to be added
     */
    public static Command addDeadline(String input) throws DateTimeParseException {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1).strip();
        String by = input.substring(input.lastIndexOf("/by") + 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Deadline d = new Deadline(name, LocalDateTime.parse(by, formatter));
        return new AddCommand(d);
    }

    /**
     * The method to return an AddCommand with a task
     *
     * @param input String to be parsed to get the Task
     * @return the AddCommand with the Task to be added
     */
    public static Command addEvent(String input) throws DateTimeParseException {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1).strip();
        String at = input.substring(input.lastIndexOf("/at") + 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Event e = new Event(name, LocalDateTime.parse(at, formatter));
        return new AddCommand(e);
    }

    public static Command parseDoneCommand(String[] userInputArr) throws DukeException {
        if (userInputArr.length > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        return new DoneCommand(getTaskNumber(userInputArr));
    }

    public static Command parseDeleteCommand(String[] userInputArr) throws DukeException {
        if (userInputArr.length > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        return new DeleteCommand(getTaskNumber(userInputArr));
    }

    public static Command parseDeadlineCommand(String input) throws DukeException {
        try {
            return addDeadline(input);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATETIME_FORMAT);
        }
    }

    public static Command parseEventCommand(String input) throws DukeException {
        try {
            return addEvent(input);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATETIME_FORMAT);
        }
    }

    /**
     * The method to get the index of the Task
     *
     * @param inputArr the array of Strings
     * @return the index of the Task to be modified
     */
    public static int getTaskNumber(String[] inputArr) {
        return Integer.parseInt(inputArr[1]) - 1;
    }

    /**
     * The method to get the keyword for a Find command
     *
     * @param input the user input
     * @return the keyword to be searched for in the list of tasks
     */
    public static String getKeyword(String input) {
        return input.substring(input.indexOf(" ")).strip();
    }
}
