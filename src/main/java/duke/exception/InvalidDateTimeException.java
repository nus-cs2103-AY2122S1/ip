package duke.exception;

/**
 * Exception of Duke that occurs when the date or time format given is invalid.
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Constructor of InvalidDateTimeException.
     */
    public InvalidDateTimeException() {
        super("Your date or time format is wrong! "
                + "Please use the format YYYY/MM/DD HHMM where the time is in 24 hours.");
    }
}
