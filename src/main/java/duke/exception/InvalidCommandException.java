package duke.exception;

/**
 * A specific {@code DukeException} that will be thrown when an input command is invalid.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs an {@code InvalidCommandException}.
     */
    public InvalidCommandException() {
        super("OOPS!!! I don't understand that!");
    }
}
