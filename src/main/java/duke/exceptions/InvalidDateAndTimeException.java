package duke.exceptions;

/**
 * Signals that the date and time is invalid or missing.
 */
public class InvalidDateAndTimeException extends DukeException {
    /**
     * Initialises a InvalidDateAndTimeException object.
     *
     * @param errorMessage contains the error message pertaining to this exception
     */
    public InvalidDateAndTimeException(String errorMessage) {
        super(errorMessage);
    }
}

