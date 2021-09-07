package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.MissingArgumentException;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {

    private enum CommandType {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE, FIND, BYE, UNKNOWN
    }

    // Returns CommandType representation of the command.
    private static CommandType toEnum(String command) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.name().equals(command.toUpperCase())) {
                return commandType;
            }
        }

        return CommandType.UNKNOWN;
    }

    // Returns hint for the possibly missing argument in the command.
    private static String getHint(CommandType commandType) {
        switch (commandType) {
        case DONE:
            // Fallthrough
        case DELETE:
            return "task number (eg. 3)";
        case EVENT:
            return "/at";
        case DEADLINE:
            return "/by";
        case FIND:
            return "any keyword (eg. book)";
        default:
            return "miss any argument";
        }
    }

    // Returns an array containing each component of the full command.
    // Checks if the command description is empty (does not apply to command with only one argument),
    // then throws exception accordingly.
    private static String[] splitCommand(String fullCommand) throws MissingArgumentException {
        String[] commandComponents = fullCommand.split(" ", 2);
        String commandPrefix = commandComponents[0];
        CommandType commandType = toEnum(commandPrefix);
        boolean hasNoArgument = (commandType.equals(CommandType.LIST) || commandType.equals(CommandType.TODO))
                || (commandType.equals(CommandType.BYE) || commandType.equals(CommandType.UNKNOWN));
        if (hasNoArgument) {
            return commandComponents;
        }

        boolean isShortCommand = commandComponents.length < 2;
        boolean isMissingArgument = isShortCommand || commandComponents[1].trim().isEmpty();
        if (isMissingArgument) {
            String hint = getHint(commandType);
            throw new MissingArgumentException(commandPrefix, hint);
        }

        return commandComponents;
    }

    // Returns command instance based on the command given by the user.
    private static Command createCommand(String fullCommand) throws DukeException {
        String[] commandComponents = splitCommand(fullCommand);
        String commandType = commandComponents[0];
        String commandDescription = commandComponents.length > 1 ? commandComponents[1] : "";
        int taskNum;

        switch (toEnum(commandType)) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        case DONE:
            taskNum = Integer.parseInt(commandComponents[1]);
            return new DoneCommand(taskNum);
        case DELETE:
            taskNum = Integer.parseInt(commandComponents[1]);
            return new DeleteCommand(taskNum);
        case TODO:
            // Fallthrough
        case EVENT:
            // Fallthrough
        case DEADLINE:
            return new AddCommand(commandType, commandDescription);
        case FIND:
            return new FindCommand(commandDescription);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Returns a command class based on the type specified in the command.
     *
     * @param fullCommand The command input by the user.
     * @return The command class based on the type specified in the command.
     * @throws MissingArgumentException The exception that handles command with empty description.
     */
    public static Command parse(String fullCommand) throws DukeException {
        boolean isEmptyCommand = fullCommand.isEmpty() || fullCommand.trim().isEmpty();

        if (isEmptyCommand) {
            throw new EmptyCommandException("Do not enter empty command!");
        }

        return createCommand(fullCommand);
    }
}
