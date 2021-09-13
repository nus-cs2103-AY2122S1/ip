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
        super("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________\n");
    }

    /**
     * Returns the error message of the DukeException.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
