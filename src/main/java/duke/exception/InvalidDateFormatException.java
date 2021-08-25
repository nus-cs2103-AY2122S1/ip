package duke.exception;

/**
 * This class represents exceptions due to invalid time format.
 */
public class InvalidDateFormatException extends DukeException{
    /***
     * Constructor to create an invalid date exception.
     *
     * @param msg The error message of the exception.
     */
    public InvalidDateFormatException(String msg) {
        super(msg);
    }
}
