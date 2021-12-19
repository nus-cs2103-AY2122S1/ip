package abyss.exception;

/**
 * Represents an exception that occurs when user inputs an invalid command.
 */
public class InvalidCommandException extends AbyssException {
    /**
     * Constructs an InvalidCommandException.
     *
     * @param message Error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
