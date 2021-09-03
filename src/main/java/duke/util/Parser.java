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

    private static String[] validateCommand(String fullCommand) throws EmptyDescriptionException {
        // Split command type and command descriptions.
        String[] commandComponents = fullCommand.split(" ", 2);

        // The message used to alert the user when command entered is empty.
        String message = "The description of [%s] command cannot be empty.";

        // Checks whether the command consists of 2 parts and if the description is specified in the command.
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
    public static Command parse(String fullCommand) throws EmptyDescriptionException {
        boolean isEmptyCommand = fullCommand.isEmpty() || fullCommand.trim().isEmpty();

        if (isEmptyCommand) {
            throw new EmptyDescriptionException("Please do not enter empty command!");
        }

        // Split command type and command descriptions.
        String[] commandComponents = fullCommand.split(" ", 2);;
        String commandType = commandComponents[0].toUpperCase();

        int taskNum;
        String commandDescription;

        Command command;

        switch (toEnum(commandType)) {
        case LIST:
            command = new ListCommand();
            break;
        case BYE:
            command = new ExitCommand();
            break;
        case DONE:
            commandComponents = validateCommand(fullCommand);
            taskNum = Integer.parseInt(commandComponents[1]);
            command = new DoneCommand(taskNum);
            break;
        case DELETE:
            commandComponents = validateCommand(fullCommand);
            taskNum = Integer.parseInt(commandComponents[1]);
            command = new DeleteCommand(taskNum);
            break;
        case TODO:
            // Fallthrough
        case EVENT:
            // Fallthrough
        case DEADLINE:
            commandComponents = validateCommand(fullCommand);
            commandDescription = commandComponents[1];
            command = new AddCommand(commandType, commandDescription);
            break;
        case FIND:
            commandComponents = validateCommand(fullCommand);
            commandDescription = commandComponents[1];
            command = new FindCommand(commandDescription);
            break;
        default:
            command = new UnknownCommand();
            break;
        }

        return command;
    }
}
