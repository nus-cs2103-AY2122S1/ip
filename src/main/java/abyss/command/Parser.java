package abyss.command;

import abyss.command.*;
import abyss.exception.InvalidCommandException;

public abstract class Parser {
    public static Command parseCommand(String command) throws InvalidCommandException {
        String[] parts = command.split("[ ]+", 2);
        String type = parts[0].toUpperCase();
        String content = "";
        if (parts.length > 1) {
            content = parts[1];
        }

        switch (type) {
        case "TODO":
            return new TodoCommand(content);
        case "DEADLINE":
            return new DeadlineCommand(content);
        case "EVENT":
            return new EventCommand(content);
        case "DONE":
            return new DoneCommand(content);
        case "DELETE":
            return new DeleteCommand(content);
        case "FIND":
            return new FindCommand(content);
        case "LIST":
            return new ListCommand();
        case "EXIT":
            return new ExitCommand();
        default:
            throw new InvalidCommandException("The Abyss does not understand your command.");
        }
    }
}
