package brobot.exception;

/**
 * Represents all exceptions specific to the Duke program.
 */
public class BroException extends Exception {
    /**
     * Constructor for the exception.
     *
     * @param errorMessage The error message;
     */
    public BroException(String errorMessage) {
        super(errorMessage);
    }
}
