package duke;

import static duke.Commands.INVALID;

import duke.DukeException.InvalidTaskIndexException;
import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a parser to make sense of user input.
 *
 * @author Adam Ho
 */
public class Parser {

    /**
     * Makes sense of the user input.
     * @param fullCommand The input from user.
     * @return A Command representation of the user's input.
     * @throws DukeException Throws a DukeException if the user's input does not comply with current features.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.isBlank()) {
            throw new DukeException("Please input a command!");
        }

        String[] words = fullCommand.split(" ", 2);
        Commands userCommand = getUserCommand(words[0]);
        String userDescription = words.length > 1 ? words[1] : "";
        String[] taskDescriptions;

        switch (userCommand) {
        case LIST:
            return new ListCommand();
        case TODO:
            taskDescriptions = getTaskDescription(userDescription, "");
            return new AddCommand(new Todo(taskDescriptions[0]));
        case DEADLINE:
            taskDescriptions = getTaskDescription(userDescription, " /by ");
            return new AddCommand(new Deadline(taskDescriptions[0], taskDescriptions[1]));
        case EVENT:
            taskDescriptions = getTaskDescription(userDescription, " /at ");
            return new AddCommand(new Event(taskDescriptions[0], taskDescriptions[1]));
        case DONE:
            return new DoneCommand(toTaskIndex(userDescription));
        case DELETE:
            return new DeleteCommand(toTaskIndex(userDescription));
        case FIND:
            return new FindCommand(userDescription);
        case CLEAR:
            return new ClearCommand();
        case EXIT:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    public static String[] getTaskDescription(String userDescription, String specifier) throws DukeException {
        if (userDescription.isBlank()) {
            throw new DukeException("oops, please add task description!");
        }
        return userDescription.split(specifier);
    }

    public static Commands getUserCommand(String userCommand) {
        for (Commands c : Commands.values()) {
            if (c.userCommand.equalsIgnoreCase(userCommand)) {
                return c;
            }
        }
        return INVALID;
    }

    /**
     * Converts the String representation of a number to an Integer representation.
     * @param userDescription The task index input by the user.
     * @return An Integer representation of the task index.
     * @throws InvalidTaskIndexException Throws an InvalidTaskException if the user does not input a valid number.
     */
    public static Integer toTaskIndex(String userDescription) throws InvalidTaskIndexException {
        try {
            return Integer.parseInt(userDescription);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
