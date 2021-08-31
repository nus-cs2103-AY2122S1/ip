package duke.exception;

/**
 * Represents an exception thrown when command is in incorrect format.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Initialise constructor for {@code UnknownCommandException}.
     */
    public UnknownCommandException() {
        super("Nee doesn't understand your command!");
    }
}
