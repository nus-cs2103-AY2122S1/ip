package bruh.exception;

/**
 * Represents an exception thrown whenever a command is called with missing parameters.
 */
public class MissingArgumentException extends BruhException {
    /**
     * Constructor for an exception thrown whenever a command is called with missing parameters.
     *
     * @param message The message describing the exception.
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}
