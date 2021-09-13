package workdone.exception;

/**
 * Represents an exception thrown when the given time is in an invalid format. A subclass of WorkDoneException.
 */
public class InvalidTimeException extends WorkDoneException {
    /**
     * Constructor of the class `InvalidTimeException`.
     *
     * @param timeFormat Correct time format.
     */
    public InvalidTimeException(String timeFormat) {
        super("☹ OOPS!!! The time is invalid.\nPlease input time in this form:\n" + timeFormat);
    }
}
