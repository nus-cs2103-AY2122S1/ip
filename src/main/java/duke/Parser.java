package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Represents a parser to understand user input and returns a Command object to be executed.
 */
public class Parser {

    /**
     * Parse a String of fullCommand and Return a valid Command object.
     * If fullCommand is invalid, exceptions are thrown.
     *
     * @param fullCommand String of the full command user inputted.
     * @return Command that can be executed.
     * @throws DukeException            If the command action is not recognized.
     * @throws IllegalArgumentException If there are missing arguments for given command.
     */
    public static Command parse(String fullCommand) throws DukeException, IllegalArgumentException {
        String[] parts = fullCommand.split(" ", 2);
        String [] args;
        String action = parts[0];
        switch (action) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "done":
            assertInputSize(parts, 2, "Not enough arguments");
            return new DoneCommand(Integer.parseInt(parts[1]));
        case "delete":
            assertInputSize(parts, 2, "Not enough arguments");
            return new DeleteCommand(Integer.parseInt(parts[1]));
        case "find":
            assertInputSize(parts, 2, "Not enough arguments");
            return new FindCommand(parts[1]);
        case "todo":
            assertInputSize(parts, 2, "☹ OOPS!!! The description of a todo cannot be empty.");
            return new AddCommand(action, parts[1]);
        case "deadline":
            assertInputSize(parts, 2, "Not enough arguments");
            args = parts[1].split(" /by ");
            assertInputSize(args, 2, "☹ OOPS!!! Insufficient args for deadline.");
            return new AddCommand(action, args);
        case "event":
            assertInputSize(parts, 2, "Not enough arguments");
            args = parts[1].split(" /at ");
            assertInputSize(args, 2, "☹ OOPS!!! Insufficient args for event.");
            return new AddCommand(action, args);
        default:
            throw new InvalidDukeCommandException();
        }
    }

    private static void assertInputSize(String[] inputs,
                                        int expectedSize,
                                        String errorMsg) throws IllegalArgumentException {
        if (inputs.length != expectedSize) {
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
