package workdone.exception;

/**
 * Represents an exception thrown when commands are invalid.
 */
public class WorkDoneException extends Exception {
    /**
     * Constructor of the class `WorkDoneException`.
     *
     * @param message Error message.
     */
    public WorkDoneException(String message) {
        super(message);
    }

    /**
     * Returns the error message of the WorkDoneException.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
