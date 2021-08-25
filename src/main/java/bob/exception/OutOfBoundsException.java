package bob.exception;

/**
 * Represents the exception thrown by Bob when the user tries to mark as completed or remove a task not inside the list.
 */
public class OutOfBoundsException extends BobException {
    /**
     * Constructor for a new OutOfBoundsException instance.
     */
    public OutOfBoundsException() {
        super();
    }
}
