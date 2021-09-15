package duke.exception;

/**
 * This class encapsulates an Exception from Duke.
 * Extends RunTimeException.
 */
public class DukeException extends RuntimeException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error: " + super.getMessage();
    }
}
