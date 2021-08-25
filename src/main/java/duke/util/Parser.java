package duke.util;

import duke.command.*;
import duke.exception.EmptyDescriptionException;

/** A class that deals with making sense of the user command. */
public class Parser {

    private enum CommandType {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE, BYE, UNKNOWN
    }

    private static CommandType toEnum(String command) {

        for (CommandType commandType : CommandType.values()) {
            if (commandType.name().equals(command)) {
                return commandType;
            }
        }
        return CommandType.UNKNOWN;
    }

    private static String[] validateCommand(String fullCommand)
            throws EmptyDescriptionException {

        String[] commandComponents = fullCommand.split(" ", 2);
        String message = "The description of [%s] command cannot be empty.";

        if (commandComponents.length < 2 || commandComponents[1].trim().isEmpty()) {
            throw new EmptyDescriptionException(String.format(message, commandComponents[0]));
        }

        return commandComponents;
    }

    /**
     * Return a command class based on the type specified in the command.
     *
     * @param fullCommand The command input by the user.
     * @return The command class based on the type specified in the command.
     * @throws EmptyDescriptionException The exception that handles command with empty description.
     */
    public static Command parse(String fullCommand)
            throws EmptyDescriptionException {

        if (fullCommand.isEmpty() || fullCommand.trim().isEmpty())
            throw new EmptyDescriptionException("Please do not enter empty command!");

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
        default:
            command = new UnknownCommand();
            break;
        }

        return command;
    }
}
