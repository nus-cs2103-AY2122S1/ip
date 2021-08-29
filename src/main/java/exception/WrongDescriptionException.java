package exception;

/**
 * Exception occurs when user does not input the correct conjunction for a specific task
 */
public class WrongDescriptionException extends DukeException {
    public WrongDescriptionException(String error) {
        super(error);
    }
}
