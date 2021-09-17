package exceptions;

/**
 * Exception raised when the user wants to access (mark complete/delete) a task who's index is out of bounds.
 *
 * @author Quan Teng Foong
 */
public class TaskDoesNotExistException extends DukeException {
    public TaskDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
