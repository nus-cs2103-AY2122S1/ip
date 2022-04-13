package bern.exception;

/**
 * Exception for commands that are invalid.
 */
public class InvalidCommandException extends BernException {
    /**
     * Constructor for InvalidCommandException.
     *
     * @param text The command that triggered the error.
     */
    public InvalidCommandException(String text) {
        super(text + " is not a command I understand");
    }
}
