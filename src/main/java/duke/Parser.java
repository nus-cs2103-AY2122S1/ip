package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ListTasksCommand;

public class Parser {
    public enum CommandType {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT,
    }

    /**
     * Parses the user's input string into a command.
     * @param fullCommand The full command string input by the user.
     * @throws An exception if the input is not a valid command.
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] commandArguments = fullCommand.split("\\s+", 2);

        String commandString = commandArguments[0];
        String arguments = commandArguments.length == 2 ? commandArguments[1] : "";

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            String fullInput = commandString + " " + arguments;
            throw new Exception("Command not recognized: " + fullInput);
        }

        // TODO: shift parsing into parser instead of individual commands.
        switch (commandType) {
        case BYE:
            return new ByeCommand(arguments);

        case LIST:
            return new ListTasksCommand(arguments);

        case DONE:
            return new CompleteTaskCommand(arguments);

        case DELETE:
            return new DeleteTaskCommand(arguments);

        case TODO:
            return new AddTodoCommand(arguments);

        case DEADLINE:
            return new AddDeadlineCommand(arguments);

        case EVENT:
            return new AddEventCommand(arguments);

        default:
            throw new Exception("No command found");
        }
    }
}
