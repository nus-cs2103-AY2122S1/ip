package exceptions;

/**
 * Exception raised when the TaskList is to be printed but it is empty.
 *
 * @author Quan Teng Foong
 */
public class EmptyTaskListException extends DukeException {
    public EmptyTaskListException(String errorMessage) {
        super(errorMessage);
    }
}
