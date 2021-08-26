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
        String action = parts[0];
        switch (action) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "done":
            if (parts.length < 2) throw new IllegalArgumentException("Not enough arguments");
            else return new DoneCommand(Integer.parseInt(parts[1]));
        case "delete":
            if (parts.length < 2) throw new IllegalArgumentException("Not enough arguments");
            else return new DeleteCommand(Integer.parseInt(parts[1]));
        case "find":
            if (parts.length < 2) throw new IllegalArgumentException("Not enough arguments");
            else return new FindCommand(parts[1]);
        case "todo":
            if (parts.length < 2)
                throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
            else return new AddCommand(action, parts[1]);
        case "deadline":
            if (parts.length < 2) throw new IllegalArgumentException("Not enough arguments");
            else {
                String[] args = parts[1].split(" /by ");
                if (args.length < 2) throw new IllegalArgumentException("☹ OOPS!!! Insufficient args for deadline.");
                return new AddCommand(action, args);
            }
        case "event":
            if (parts.length < 2) throw new IllegalArgumentException("Not enough arguments");
            else {
                String[] args = parts[1].split(" /at ");
                if (args.length < 2) throw new IllegalArgumentException("☹ OOPS!!! Insufficient args for event.");
                return new AddCommand(action, args);
            }
        }

        throw new InvalidDukeCommandException();
    }
}
