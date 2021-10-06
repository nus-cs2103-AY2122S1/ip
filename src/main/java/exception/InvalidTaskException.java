package exception;

/**
 * Exception occurs when user inputs command not supported by Duke
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String error) {
        super(error);
    }
}
