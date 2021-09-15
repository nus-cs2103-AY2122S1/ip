package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UndoneCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
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
    private static final String MISSING_NUMBER = "Indicate a task number beside the command ☻";
    private static final String MISSING_KEYWORD = "Enter a keyword beside the command ☻";

    private Duke duke;

    /** The constructor of a Parser object */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * The method to parse an input line by the user
     *
     * @param input the String line to be parsed
     * @return the command to be executed
     * @throws DukeException
     */
    public Command parse(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("Type something...");
        }
        String[] x = input.split(" ");
        String cmd = x[0];
        assert(cmd != null);
        if (x.length == 1) {
            if (cmd.equals("bye")) {
                return new ExitCommand();
            } else if (cmd.equals("list")) {
                return new ListCommand();
            } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                missingTaskName(cmd);
            } else if (cmd.equals("done") || cmd.equals("delete")) {
                throw new DukeException(MISSING_NUMBER);
            } else if (cmd.equals("find")) {
                throw new DukeException(MISSING_KEYWORD);
            } else if (cmd.equals("undo")) {
                return getUndoCommand(this.duke.getPreviousCommand());
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

    /**
     * The method to return an DoneCommand from a user input
     *
     * @param userInputArr String that the user input for the DoneCommand
     * @return the DoneCommand to be executed
     */
    public Command parseDoneCommand(String[] userInputArr) throws DukeException {
        if (userInputArr.length > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        int index = getTaskNumber(userInputArr);
        return new DoneCommand(index);
    }

    /**
     * The method to return an DeleteCommand from a user input
     *
     * @param userInputArr String that the user input for the DeleteCommand
     * @return the DeleteCommand to be executed
     */
    public Command parseDeleteCommand(String[] userInputArr) throws DukeException {
        if (userInputArr.length > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        int index = getTaskNumber(userInputArr);
        return new DeleteCommand(index, this.duke.getTaskList());
    }

    /**
     * The method to return an AddCommand from a user input
     *
     * @param input String that the user input to add a Deadline
     * @return the AddCommand to be executed
     */
    public static Command parseDeadlineCommand(String input) throws DukeException {
        try {
            return addDeadline(input);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATETIME_FORMAT);
        }
    }

    /**
     * The method to return an AddCommand from a user input
     *
     * @param input String that the user input to add an Event
     * @return the AddCommand to be executed
     */
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

    /**
     * The method to parse an "undo" command by user and return the respective
     * command to be carried out.
     *
     * @param prevCommand the previous command that the user wants to undo
     * @return the command to help undo the previous command
     * @throws DukeException
     */
    public Command getUndoCommand(Command prevCommand) throws DukeException {
        if (prevCommand == null) {
            throw new DukeException("No previous command to undo");
        }
        if (prevCommand instanceof AddCommand) {
            if (((AddCommand) prevCommand).isAlreadyUndone()) {
                throw new DukeException("Previous command cannot be undone");
            }
            int index = this.duke.getTaskList().size() - 1;
            return new DeleteCommand(index, this.duke.getTaskList(), true);
        } else if (prevCommand instanceof DeleteCommand) {
            if (((DeleteCommand) prevCommand).isAlreadyUndone()) {
                throw new DukeException("Previous command cannot be undone");
            }
            Task prevDeletedTask = ((DeleteCommand) prevCommand).getDeletedTask();
            int prevDeletedIndex = ((DeleteCommand) prevCommand).getDeletedIndex();
            return new AddCommand(prevDeletedTask, prevDeletedIndex);
        } else if (prevCommand instanceof DoneCommand) {
            return new UndoneCommand(((DoneCommand) prevCommand).getIndex());
        } else {
            // the command cannot be undone
            throw new DukeException("Previous command cannot be undone");
        }
    }
}
