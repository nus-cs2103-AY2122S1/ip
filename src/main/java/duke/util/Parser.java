package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.IncompleteDescriptionException;
import duke.exception.InvalidDateFormatException;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {

    private enum CommandType {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE, FIND, BYE, UNKNOWN
    }

    private static CommandType toEnum(String command) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.name().equals(command)) {
                return commandType;
            }
        }

        return CommandType.UNKNOWN;
    }

    private static String[] splitCommand(String fullCommand) throws EmptyDescriptionException {
        String[] commandComponents = fullCommand.split(" ", 2);
        String message = "The description of [%s] command cannot be empty.";

        boolean isShortCommand = commandComponents.length < 2;
        boolean isEmptyDescription = isShortCommand || commandComponents[1].trim().isEmpty();

        if (isEmptyDescription) {
            throw new EmptyDescriptionException(String.format(message, commandComponents[0]));
        }

        return commandComponents;
    }

    /**
     * Returns a command class based on the type specified in the command.
     *
     * @param fullCommand The command input by the user.
     * @return The command class based on the type specified in the command.
     * @throws EmptyDescriptionException The exception that handles command with empty description.
     */
    public static Command parse(String fullCommand) throws EmptyDescriptionException, IncompleteDescriptionException,
            InvalidDateFormatException {
        boolean isEmptyCommand = fullCommand.isEmpty() || fullCommand.trim().isEmpty();

        if (isEmptyCommand) {
            throw new EmptyDescriptionException("Please do not enter empty command!");
        }

        String[] commandComponents = fullCommand.split(" ", 2);;
        String commandType = commandComponents[0].toUpperCase();

        int taskNum;
        String commandDescription;

        switch (toEnum(commandType)) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        case DONE:
            commandComponents = splitCommand(fullCommand);
            taskNum = Integer.parseInt(commandComponents[1]);
            return new DoneCommand(taskNum);
        case DELETE:
            commandComponents = splitCommand(fullCommand);
            taskNum = Integer.parseInt(commandComponents[1]);
            return new DeleteCommand(taskNum);
        case TODO:
            // Fallthrough
        case EVENT:
            // Fallthrough
        case DEADLINE:
            commandComponents = splitCommand(fullCommand);
            commandDescription = commandComponents[1];
            return new AddCommand(commandType, commandDescription);
        case FIND:
            commandComponents = splitCommand(fullCommand);
            commandDescription = commandComponents[1];
            return new FindCommand(commandDescription);
        default:
            return new UnknownCommand();
        }
    }
}
