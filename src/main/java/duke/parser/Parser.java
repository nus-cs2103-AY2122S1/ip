package duke.parser;

import duke.command.*;

/**
 * The Parser class encapsulates the dealing of user commands by Duke.
 */
public class Parser {
    /**
     * Produces the corresponding type of executable command from the user input.
     * @param fullCommand The full command input by the user.
     * @return The corresponding type of executable command from the user input.
     */
    public Command parse(String fullCommand) {
        Command command = null;
        String[] commandSplit = fullCommand.split(" ", 2);

        // Determine the type of command and initialise the according command
        switch(commandSplit[0]) {
        case "bye":
            command = new ExitCommand();
            break;
        case "todo":
            command = new TodoCommand(fullCommand);
            break;
        case "deadline":
            command = new DeadlineCommand(fullCommand);
            break;
        case "event":
            command = new EventCommand(fullCommand);
            break;
        case "delete":
            command = new DeleteCommand(commandSplit[1]);
            break;
        case "done":
            command = new DoneCommand(commandSplit[1]);
            break;
        case "list":
            command = new ListCommand();
            break;
        case "find":
            command = new FindCommand(commandSplit[1]);
            break;
        default:
            command = new ErrorCommand();
            break;
        }

        return command;
    }
}
