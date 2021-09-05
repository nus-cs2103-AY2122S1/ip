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
        assert fullCommand != null : "Commands should not be null";
        String[] parts = fullCommand.split(" ", 2);
        String[] args;
        String action = parts[0];
        switch (action) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "done":
            assertArgumentIsPresent(fullCommand);
            return new DoneCommand(Integer.parseInt(parts[1]));
        case "delete":
            assertArgumentIsPresent(fullCommand);
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
        case "find":
            assertArgumentIsPresent(fullCommand);
            return new FindCommand(parts[1]);
        case "todo":
            assertDescriptionIsPresent(fullCommand);
            return new AddCommand(action, parts[1]);
        case "deadline":
            assertDescriptionIsPresent(fullCommand);
            assertTimeIsPresent(fullCommand, " /by ");
            args = parts[1].split(" /by ");
            return new AddCommand(action, args);
        case "event":
            assertDescriptionIsPresent(fullCommand);
            assertTimeIsPresent(fullCommand, " /at ");
            args = parts[1].split(" /at ");
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

    private static void assertArgumentIsPresent(String fullCommand) {
        String[] inputs = fullCommand.split(" ", 2);
        assertInputSize(inputs, 2, "Not enough arguments");
    }

    private static void assertDescriptionIsPresent(String fullCommand) {
        String[] inputs = fullCommand.split(" ", 2);
        String errorMsg = String.format("☹ OOPS!!! The description of a %s cannot be empty.", inputs[0]);
        assertInputSize(inputs, 2, errorMsg);
    }

    private static void assertTimeIsPresent(String fullCommand, String delimiter) {
        String[] inputs = fullCommand.split(" ", 2);
        String[] args = inputs[1].split(delimiter, 2);
        String errorMsg = String.format("☹ OOPS!!! Insufficient args for %s.", inputs[0]);
        assertInputSize(args, 2, errorMsg);
    }
}
