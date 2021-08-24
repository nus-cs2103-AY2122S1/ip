package exceptions;

/**
 * Exception raised when there is no name for the task that was input by the user.
 *
 * @author Quan Teng Foong
 */
public class NoTaskNameException extends DukeException {
    public NoTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
