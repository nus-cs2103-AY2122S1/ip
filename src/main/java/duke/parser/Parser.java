package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.ToDo;

/**
 * This class abstracts the parsing of user input.
 *
 * @author Chesterwongz
 */
public class Parser {
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Parses the user's input.
     *
     * @param input The input String.
     * @return The user's desired command.
     * @throws DukeException The Exception to be thrown when the user's input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.contains("|")) {
            throw new DukeException("OOPS!!! Your input must not contain \"|\"");
        }
        switch (input) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        default:
            String[] split = input.split(" ", 2);
            switch (split[0]) {
            case FindCommand.COMMAND_WORD:
                return findTask(split);
            case DoneCommand.COMMAND_WORD:
                return doTask(split);
            case ToDoCommand.COMMAND_WORD:
                return addToDo(split);
            case DeadlineCommand.COMMAND_WORD:
                return addDeadline(split);
            case EventCommand.COMMAND_WORD:
                return addEvent(split);
            case DeleteCommand.COMMAND_WORD:
                return deleteTask(split);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
            }
        }
    }

    private static FindCommand findTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please type what you want to find");
        }
        return new FindCommand(input[1]);
    }
    /**
     * Adds a To-do task to the taskList.
     *
     * @param input The String array containing the to-do description at index 1.
     * @return The Command to be executed.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static ToDoCommand addToDo(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        }
        return new ToDoCommand(new ToDo(input[1]));
    }

    /**
     * Adds a deadline task to the taskList.
     *
     * @param input The String array containing the deadline description at index 1.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static DeadlineCommand addDeadline(String[] input) throws DukeException {
        String formatErrorMsg = "OOPS!!! Please add the deadline with the right format:\n"
                + "  deadline <description> /by <" + DATE_TIME_FORMAT + ">";
        if (input.length < 2) {
            throw new DukeException(formatErrorMsg);
        }
        String[] deadline = input[1].split("/", 2);
        if (deadline[0].strip().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
        }
        if (deadline.length < 2 || !deadline[1].startsWith("by") || deadline[1].length() < 4) {
            throw new DukeException(formatErrorMsg);
        }
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            return new DeadlineCommand(
                    new Deadline(deadline[0], LocalDateTime.parse(deadline[1].substring(3), format)));
        } catch (DateTimeParseException e) {
            throw new DukeException(formatErrorMsg);
        }
    }

    /**
     * Adds an event task to the taskList.
     *
     * @param input The String array containing the event description at index 1.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static EventCommand addEvent(String[] input) throws DukeException {
        String formatErrorMsg = "OOPS!!! Please add the event with the right format:\n"
                + "  event <description> /at <" + DATE_TIME_FORMAT + ">";
        if (input.length < 2) {
            throw new DukeException(formatErrorMsg);
        }
        String[] event = input[1].split("/", 2);
        if (event[0].strip().isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty");
        }
        if (event.length < 2 || !event[1].startsWith("at") || event[1].length() < 4) {
            throw new DukeException(formatErrorMsg);
        }
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            return new EventCommand(
                    new Event(event[0], LocalDateTime.parse(event[1].substring(3), format)));
        } catch (DateTimeParseException e) {
            throw new DukeException(formatErrorMsg);
        }
    }

    /**
     * Marks task as done in taskList.
     *
     * @param input The String array with the index of the task to be done in input[1], if it is there.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static DoneCommand doTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be marked as done");
        }
        try {
            String[] tasksToBeDone = input[1].trim().split(" ");
            Integer[] taskIntegersToBeDone = new Integer[tasksToBeDone.length];
            for (int i = 0; i < tasksToBeDone.length; i++) {
                taskIntegersToBeDone[i] = Integer.parseInt(tasksToBeDone[i]) - 1;
            }
            return new DoneCommand(taskIntegersToBeDone);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param input The String array with the index of the task to be removed in input[1], if it is there.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static DeleteCommand deleteTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be removed");
        }
        try {
            String[] taskStringsToDelete = input[1].trim().split(" ");
            Integer[] taskIntegersToDelete = new Integer[taskStringsToDelete.length];
            for (int i = 0; i < taskStringsToDelete.length; i++) {
                taskIntegersToDelete[i] = Integer.parseInt(taskStringsToDelete[i]) - 1;
            }
            return new DeleteCommand(taskIntegersToDelete);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
    }
}
