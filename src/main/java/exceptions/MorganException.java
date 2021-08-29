package exceptions;

/**
 * This class encapsulates all checked exceptions in Morgan.
 */
public class MorganException extends Exception {
    /**
     * Constructor for MorganException.
     * @param message The error message to be displayed to user.
     */
    public MorganException(String message) {
        super(message);
    }
}
