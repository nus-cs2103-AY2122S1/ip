package duke;

import duke.command.*;

public class Parser {

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
            if (parts.length < 2) throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
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
