package duke.exception;

/**
 * Represents an exception caused when an unrecognized command is used in the Duke program
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public InvalidCommandException() {
        super("Oops, unfortunately i am not yet smart enough to understand what you are saying");
    }
}
