package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parses the user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Returns the corresponding command for the user input
     * @param input User input
     * @return the corresponding command for the user input
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        switch (input.split(" ")[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand(input);
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