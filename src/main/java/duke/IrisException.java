package duke;

/**
 * Represents an exception thrown by Iris
 */
public class IrisException extends Exception {
    /**
     * Create a new IrisException object
     * @param message error message for the exception
     */
    public IrisException(String message) {
        super(message);
    }
}
