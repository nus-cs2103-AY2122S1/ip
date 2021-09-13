package duke.utils;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;

/**
 * Parser reads the input by the user and returns the relevant duke.command.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Parser {

    /**
     * Reads the fullCommand and return the relevant duke.command.
     *
     * @param fullCommand the fullCommand
     * @return the relevant command
     */
    public static Command parse(String fullCommand) {
        String[] commandPair = fullCommand.split(" ", 2);
        String description = commandPair.length == 1 ? "" : commandPair[1];
        switch (commandPair[0]) {
        case "bye":
            return new ByeCommand(description);
        case "list":
            return new ListCommand(description);
        case "done":
            return new DoneCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "todo":
            return new ToDoCommand(description);
        case "deadline":
            return new DeadlineCommand(description);
        case "event":
            return new EventCommand(description);
        case "find":
            return new FindCommand(description);
        default:
            return new InvalidCommand(description);
        }
    }


}
