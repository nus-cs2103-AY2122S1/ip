package Duke.exception;

/**
 * Represents an exception which occurs
 * during the execution of the Duke app.
 */
public class DukeException extends Exception {

    public DukeException(String error) {
        super(error);
    }
}
