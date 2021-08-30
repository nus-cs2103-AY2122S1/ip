package brobot.exception;

/**
 * Represents an exception caused when an unrecognized command is used in the Duke program
 */
public class InvalidCommandException extends BroException {
    /**
     * Constructor for the exception.
     */
    public InvalidCommandException() {
        super("Eh bro, I don't understand what you are saying lah");
    }
}
