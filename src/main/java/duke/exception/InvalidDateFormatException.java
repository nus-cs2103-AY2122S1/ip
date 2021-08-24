package duke.exception;

/** An exception that handles command description with invalid date format. */
public class InvalidDateFormatException extends DukeException {

    /**
     * A constructor for class InvalidDateFormatException.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
