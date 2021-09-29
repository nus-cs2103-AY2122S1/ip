package Duke;

import Duke.command.*;
import Duke.exception.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        switch (input.split(" ")[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(input);
            case "todo":
                return new TodoCommand(input);
            case "deadline":
                return new DeadlineCommand(input);
            case "event":
                return new EventCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "find":
                return new FindCommand(input);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}