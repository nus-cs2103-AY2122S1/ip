package duke.exception;

/**
 * Represents an exception caused by user providing a date in a format that cannot be parsed.
 */
public class InvalidDateTimeFormatException extends DukeException {
    /**
     * Constructor for InvalidDateTimeFormatException.
     */
    public InvalidDateTimeFormatException() {
        super("Cannot read invalid datetime format.");
    }
}
