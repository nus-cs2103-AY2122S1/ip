package duke.exception;

/**
 * Represents an exception thrown when date format is invalid.
 */
public class InvalidDateException extends DukeException {

    /**
     * Initialises constructor for the exception {@code InvalidDateException}.
     */
    public InvalidDateException() {
        super("Invalid date format, should be [dd/MM/yy] [HHmm]");
    }
}
