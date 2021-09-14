package exception;

/**
 * Encapsulates an exception when the start time is after the end time in a time period.
 */
public class InvalidTimePeriodException extends DukeException {
    /**
     * Instantiates an exception when the start time is after the end time in a time period.
     */
    public InvalidTimePeriodException() {
        super("Start time cannot be after end time. The first time must be the start time.");
    }
}
