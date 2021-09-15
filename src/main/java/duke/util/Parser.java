package duke.util;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {

    private enum CommandType {
        LIST, DONE, TAG, TODO, EVENT, DEADLINE, DELETE, FIND, BYE, UNKNOWN
    }

    // Returns the array consisting command components.
    private static String[] toComponents(String command) {
        return command.split(" ", 2);
    }

    // Returns CommandType representation of the command.
    private static CommandType toType(String command) {
        String commandTypeByUser = toComponents(command)[0].toUpperCase();
        for (CommandType commandType : CommandType.values()) {
            String validType = commandType.name();
            boolean isValid = commandTypeByUser.equals(validType);
            if (isValid) {
                return commandType;
            }
        }

        return CommandType.UNKNOWN;
    }

    // Returns the description of the command.
    private static String toDescription(String command) {
        String[] components = toComponents(command);
        return components.length < 2 ? "" : components[1];
    }

    // Returns command instance based on the command given by the user.
    private static Command createCommand(String command) throws DukeException {
        CommandType commandType = toType(command);
        switch (commandType) {
        case LIST:
            return new ListCommand(command);
        case BYE:
            return new ExitCommand(command);
        case DONE:
            return new DoneCommand(toDescription(command));
        case DELETE:
            return new DeleteCommand(toDescription(command));
        case TODO:
            return new TodoCommand(toDescription(command));
        case EVENT:
            return new EventCommand(toDescription(command));
        case DEADLINE:
            return new DeadlineCommand(toDescription(command));
        case FIND:
            return new FindCommand(toDescription(command));
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Returns a command class based on the type specified in the command.
     *
     * @param command The command input by the user.
     * @return The command class based on the type specified in the command.
     * @throws EmptyCommandException The exception being thrown when the empty command is detected.
     */
    public static Command parse(String command) throws DukeException {
        ExceptionChecker.checkEmptyCommand(command);

        return createCommand(command);
    }
}
