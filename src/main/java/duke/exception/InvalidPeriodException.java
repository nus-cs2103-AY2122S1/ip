package duke.exception;

/**
 * Represents an exception caused by user providing a chronologically invalid time period.
 */
public class InvalidPeriodException extends DukeException {
    /**
     * Constructor for InvalidPeriodException.
     *
     * @param startDateString User input for start date.
     * @param endDateString User input for end date.
     */
    public InvalidPeriodException(String startDateString, String endDateString) {
        super("Time period provided is invalid as " + startDateString + " is later than " + endDateString + ".");
    }
}
