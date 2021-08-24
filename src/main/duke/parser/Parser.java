package duke.parser;

import duke.command.*;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class abstracts the parsing of user input.
 *
 * @author Chesterwongz
 */
public class Parser {
    private static final String DATE_TIME_FORMAT = "dd-MM-yyy HH:mm";

    /**
     * Parses the user's input.
     *
     * @param input The input String.
     * @return The user's desired command.
     * @throws DukeException The Exception to be thrown when the user's input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.contains("|")) {
            throw new DukeException("OOPS!!! Your input must not contain \"|\" ☹");
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
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means ☹ ");
            }
        }
    }

    private static FindCommand findTask(String[] input) throws DukeException {
        if (input.length > 1) {
            return new FindCommand(input[1]);
        } else {
            throw new DukeException("OOPS!!! Please type what you want to find ☹");
        }
    }
    
    /**
     * Adds a To-do task to the taskList.
     *
     * @param input The String array containing the to-do description at index 1.
     * @return The Command to be executed.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static ToDoCommand addToDo(String[] input) throws DukeException {
        if (input.length > 1) {
            return new ToDoCommand(new ToDo(input[1]));
        } else {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty ☹");
        }
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
        if (input.length > 1) {
            String[] deadline = input[1].split("/", 2);
            if (deadline.length > 1 && deadline[1].length() > 3) {
                try {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    return new DeadlineCommand(
                            new Deadline(deadline[0], LocalDateTime.parse(deadline[1].substring(3), format)));
                } catch (DateTimeParseException e) {
                    throw new DukeException(formatErrorMsg);
                }
            } else {
                throw new DukeException(formatErrorMsg);
            }
        } else {
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
        if (input.length > 1) {
            String[] event = input[1].split("/", 2);
            if (event.length > 1 && event[1].length() > 3) {
                try {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    return new EventCommand(
                            new Event(event[0], LocalDateTime.parse(event[1].substring(3), format)));
                } catch (DateTimeParseException e) {
                    throw new DukeException(formatErrorMsg);
                }
            } else {
                throw new DukeException(formatErrorMsg);
            }
        } else {
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
            throw new DukeException("OOPS!!! Please input the task number to be marked as done ☹");
        }
        try {
            int taskID = Integer.parseInt(input[1]) - 1;
            return new DoneCommand(taskID);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
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
            throw new DukeException("OOPS!!! Please input the task number to be removed ☹");
        }
        try {
            int index = Integer.parseInt(input[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }
}
