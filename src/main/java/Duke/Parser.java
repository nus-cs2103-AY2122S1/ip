package Duke;

import Duke.DukeException;
import Duke.Keyword;

public class Parser {
    /**
     * Checks if command is valid keyword.
     *
     * @param input command
     * @return Duke.Keyword
     * @throws DukeException if command is invalid keyword.
     */
    public static Keyword parse(String input) throws DukeException {
        switch (input) {
        case "list":
            return Keyword.LIST;
        case "done":
            return Keyword.DONE;
        case "todo":
            return Keyword.TODO;
        case "deadline":
            return Keyword.DEADLINE;
        case "event":
            return Keyword.EVENT;
        case "delete":
            return Keyword.DELETE;
        case "find":
            return Keyword.FIND;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
