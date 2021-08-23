package iris;

/**
 * Represents an exception thrown by Iris
 */
public class IrisException extends Exception {
    /**
     * Creates a new IrisException object
     *
     * @param message error message for the exception
     */
    public IrisException(String message) {
        super(message);
    }
}
