package duke.exception;

/**
 * Signals that a time was not provided with the input
 */
public class NoTimeException extends DukeException {
    public NoTimeException(String errorMessage) {
        super(errorMessage);
    }
}
