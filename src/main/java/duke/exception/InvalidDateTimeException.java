package duke.exception;

/**
 * Exception thrown when an invalid date time is found.
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Creates a new exception when an invalid date time is found.
     * @param invalidDateTime Invalid date time.
     * @param formatMessage Message to show to describe the date time format.
     */
    public InvalidDateTimeException(String invalidDateTime, String formatMessage) {
        super("Invalid datetime: " + invalidDateTime + "\n" + formatMessage);
    }
}
