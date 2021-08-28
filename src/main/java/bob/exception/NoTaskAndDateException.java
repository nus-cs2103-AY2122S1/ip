package bob.exception;

/**
 * Represents the exception thrown by Bob when the user does not specify the task description
 * and event or deadline date.
 */
public class NoTaskAndDateException extends BobException {
    /**
     * Constructor for a new NoTaskAndDateException instance.
     */
    public NoTaskAndDateException() {
        super();
    }
}
