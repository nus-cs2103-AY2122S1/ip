package duke.exception;

/**
 * An exception that handles command description with invalid date format.
 */
public class InvalidDateFormatException extends DukeException {
    /**
     * Constructs an InvalidDateFormatException instance that handles command description with invalid date format.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
