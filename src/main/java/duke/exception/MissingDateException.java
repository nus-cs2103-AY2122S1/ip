package duke.exception;

/**
 * Represents an exception caused by user not providing the expected number of dates.
 */
public class MissingDateException extends DukeException {
    /**
     * Constructor for MissingDateException.
     */
    public MissingDateException() {
        super("Insufficient date input provided.");
    }
}
