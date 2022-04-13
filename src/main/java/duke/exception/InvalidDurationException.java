package duke.exception;

/**
 * Exception of Duke that occurs when the date or time format in event given is invalid.
 */
public class InvalidDurationException extends DukeException {
    /**
     * Constructor of InvalidDurationException.
     */
    public InvalidDurationException() {
        super("Please define the duration of your event in the following format: "
                + "YYYY/MM/DD HHMM - HHMM in the 24 hours format.");
    }
}
