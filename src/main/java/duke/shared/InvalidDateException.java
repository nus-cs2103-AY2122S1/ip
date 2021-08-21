package duke.shared;

/**
 * A subclass of DukeException specialized for the case of a user providing invalid dates, date ranges or
 * other date related objects.
 */
public class InvalidDateException extends DukeException {
    /**
     * Create InvalidDateException with default messages.
     */
    public InvalidDateException() {
        super(DukeException.ExceptionCode.INCORRECT_ARGS, "Invalid date(s)");
    }

    /**
     * Create InvalidDateException with a custom message.
     * @param message Custom message.
     */
    public InvalidDateException(String message) {
        super(DukeException.ExceptionCode.INCORRECT_ARGS, message);
    }
}
