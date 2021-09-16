package duke.exception;

/** Exception to be thrown when a date has the incorrect format */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructor for an InvalidDateFormatException
     */
    public InvalidDateFormatException() {
        super("Invalid date format!");
    }
}
