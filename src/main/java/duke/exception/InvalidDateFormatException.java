package duke.exception;

/**
 * Signals that the given date format is invalid.
 */
public class InvalidDateFormatException extends DukeException {
    /**
     * Constructs an InvalidDateFormatException.
     */
    public InvalidDateFormatException() {
        super("Please input date in yyyy-mm-dd format.");
    }
}
