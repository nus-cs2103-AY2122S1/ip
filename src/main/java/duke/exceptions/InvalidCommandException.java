package duke.exceptions;

public class InvalidCommandException extends DukeException {
    /**
     * Creates an exception to handle invalid user command
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
