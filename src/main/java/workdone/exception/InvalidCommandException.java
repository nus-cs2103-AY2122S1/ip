package duke.exception;

/**
 * Represents an exception thrown when commands received are unknown. A subclass of DukeException.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor of the class `InvalidCommandException`.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
