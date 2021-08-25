package duke.exception;

/**
 * Represents an exception thrown when an invalid task number is given. A subclass of DukeException.
 */
public class InvalidTaskNoException extends DukeException {
    /**
     * Constructor of the class `InvalidTaskNoException`.
     */
    public InvalidTaskNoException() {
        super("☹ OOPS!!! The task number is invalid.");
    }
}
