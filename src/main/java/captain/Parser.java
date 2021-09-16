package captain;

import static captain.Commands.INVALID;

import captain.DukeException.InvalidTaskIndexException;
import captain.command.AddCommand;
import captain.command.ClearCommand;
import captain.command.Command;
import captain.command.DeleteCommand;
import captain.command.DoneCommand;
import captain.command.ExitCommand;
import captain.command.FindCommand;
import captain.command.InvalidCommand;
import captain.command.ListCommand;
import captain.command.SortCommand;
import captain.task.Deadline;
import captain.task.Event;
import captain.task.Todo;



/**
 * Represents a parser to make sense of user input.
 *
 * @author Adam Ho
 */
public class Parser {

    public static final String NO_SPECIFIER = "";
    public static final String BY_SPECIFIER = " /by ";
    public static final String AT_SPECIFIER = " /at ";
    public static final String FIND_SPECIFIER = ", ";

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
            taskDescriptions = getTaskDescriptions(userDescription, NO_SPECIFIER);
            return new AddCommand(new Todo(taskDescriptions[0]));
        case DEADLINE:
            taskDescriptions = getTaskDescriptions(userDescription, BY_SPECIFIER);
            return new AddCommand(new Deadline(taskDescriptions[0], taskDescriptions[1]));
        case EVENT:
            taskDescriptions = getTaskDescriptions(userDescription, AT_SPECIFIER);
            return new AddCommand(new Event(taskDescriptions[0], taskDescriptions[1]));
        case DONE:
            return new DoneCommand(toTaskIndex(userDescription));
        case DELETE:
            return new DeleteCommand(toTaskIndex(userDescription));
        case FIND:
            taskDescriptions = getTaskDescriptions(userDescription, FIND_SPECIFIER);
            return new FindCommand(taskDescriptions);
        case CLEAR:
            return new ClearCommand();
        case SORT:
            return new SortCommand();
        case EXIT:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    public static String[] getTaskDescriptions(String userDescription, String specifier) throws DukeException {
        if (userDescription.isBlank()) {
            throw new DukeException("oops, please add task description!");
        }
        if (specifier.isBlank()) {
            return new String[] {userDescription};
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
