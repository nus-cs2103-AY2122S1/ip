package duke.exception;

/**
 * Represents an exception caused by user not providing a date.
 */
public class MissingDateException extends DukeException {
    /**
     * Constructor for MissingDateException.
     */
    public MissingDateException() {
        super("No date is provided.");
    }
}
