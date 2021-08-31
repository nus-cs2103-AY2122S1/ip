package duke.exception;

/**
 * Represents an exception thrown when date format is invalid.
 */
public class InvalidDateException extends DukeException {

    /**
     * Initialise constructor for {@code InvalidDateException}.
     */
    public InvalidDateException() {
        super("Invalid date format, should be [dd/MM/yy] [HHmm]");
    }
}
