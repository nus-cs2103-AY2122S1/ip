package duke.exception;

/**
 * Represents an exception thrown when command is in incorrect format.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Initialises constructor for the exception {@code UnknownCommandException}.
     */
    public UnknownCommandException() {
        super("Nee doesn't understand your command!");
    }
}
