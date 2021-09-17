package duke.exception;

/**
 * The exception thrown when the date format of a Deadline or an Event is incorrect.
 */
public class DateFormatException extends DukeException {
    @Override
    public String getMessage() {
        return "Duke cannot parse the date! Please ensure the date is in the format dd-MM-yyyy";
    }
}
