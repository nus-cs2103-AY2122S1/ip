package bobbybot.exceptions;

/**
 * Exception for task number out of bounds
 */
public class TaskNoOutOfBoundsException extends BobbyException {
    public TaskNoOutOfBoundsException(String message) {
        super(message);
    }
}
