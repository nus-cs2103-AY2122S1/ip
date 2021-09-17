package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.FilterTasksCommand;
import duke.command.ListTasksCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

public class Parser {
    public enum CommandType {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, FIND,
    }

    /**
     * Parses the user's input string into a command.
     *
     * @param fullCommand The full command string input by the user.
     * @return The new parsed {@link Command}.
     * @throws DukeException An exception if the input is not a valid command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandArguments = fullCommand.split("\\s+", 2);

        String commandString = commandArguments[0];
        String arguments = commandArguments.length == 2 ? commandArguments[1] : "";

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Command not recognized: " + fullCommand);
        }

        switch (commandType) {
        case BYE:
            return new ByeCommand(arguments);

        case LIST:
            return new ListTasksCommand(arguments);

        case DONE:
            return new CompleteTaskCommand(arguments);

        case DELETE:
            return new DeleteTaskCommand(arguments);

        case FIND:
            return new FilterTasksCommand(arguments);

        case TODO:
            return new AddTodoCommand(arguments);

        case DEADLINE:
            return new AddDeadlineCommand(arguments);

        case EVENT:
            return new AddEventCommand(arguments);

        default:
            throw new InvalidCommandException("Command not recognized: " + fullCommand);
        }
    }
}
