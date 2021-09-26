package duke.exception;

/**
 * Signals that an unknown command has been encountered.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructs an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
