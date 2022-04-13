package workdone.exception;

/**
 * Represents an exception thrown when an invalid task number is given. A subclass of WorkDoneException.
 */
public class InvalidTaskNoException extends WorkDoneException {
    /**
     * Constructor of the class `InvalidTaskNoException`.
     */
    public InvalidTaskNoException() {
        super("☹ OOPS!!! The task number is invalid.");
    }
}
