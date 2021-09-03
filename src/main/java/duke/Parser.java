package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.DukeException.InvalidTaskIndexException;
import static duke.Commands.INVALID;

/**
 * Represents a parser to make sense of user input.
 *
 * @author Adam Ho
 */
public class Parser {

    /**
     * Makes sense of the user input.
     * @param fullCommand
     * @return
     * @throws DukeException
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

    public static Integer toTaskIndex(String userDescription) throws InvalidTaskIndexException {
        try {
            return Integer.parseInt(userDescription);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
