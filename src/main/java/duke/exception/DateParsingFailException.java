package duke.exception;

/**
 * An exception that handles the failure of parsing date string to LocalDate.
 */
public class DateParsingFailException extends DukeException {

    /**
     * Constructs a DukeException instance that handles the failure of parsing date string to LocalDate.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public DateParsingFailException(String message) {
        super(message);
    }
}
