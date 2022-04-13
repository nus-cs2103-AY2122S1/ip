package duke.utils;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TaskCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/** Parser class to parse a user's input */
public class Parser {

    /**
     * Gets the command entered by the user.
     *
     * @param userInput The string keyed in by the user
     * @return The first word keyed in by the user
     */
    public static String getUserCommand(String userInput) {
        if (userInput.indexOf(' ') <= 0) {
            return userInput;
        }
        return userInput.substring(0, userInput.indexOf(' '));
    }

    /**
     * Gets the argument entered by the user.
     *
     * @param userInput The string keyed in by the user
     * @return The arguments entered by the user (all words after the first word)
     */
    public static String getUserArgument(String userInput) {
        if (userInput.indexOf(' ') <= 0) {
            return "";
        }
        try {
            return userInput.substring(userInput.indexOf(' ') + 1).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Parses a user's input and returns the appropriate command for Duke to execute
     *
     * @param userInput The user's String input
     * @return The appropriate Command to execute based on the user's input
     */
    public static Command parse(String userInput) throws DukeException {
        String userCommand = getUserCommand(userInput);
        String userArgument = getUserArgument(userInput);

        switch (userCommand) {
        case Command.LIST_COMMAND:
            return new ListCommand(userCommand, userArgument);
        case Command.BYE_COMMAND:
            return new ByeCommand(userCommand, userArgument);
        case Command.DONE_COMMAND:
            return new DoneCommand(userCommand, userArgument);
        case Command.TODO_COMMAND:
        case Command.DEADLINE_COMMAND:
        case Command.EVENT_COMMAND:
            return new TaskCommand(userCommand, userArgument);
        case Command.DELETE_COMMAND:
            return new DeleteCommand(userCommand, userArgument);
        case Command.FIND_COMMAND:
            return new FindCommand(userCommand, userArgument);
        default:
            throw new InvalidCommandException();
        }
    }
}
