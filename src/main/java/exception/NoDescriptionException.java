package exception;

/**
 * Exception occurs when user does not input the necessary description
 */
public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String error) {
        super(error);
    }
}
