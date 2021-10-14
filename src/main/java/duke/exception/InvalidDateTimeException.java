package duke.exception;

/**
 * Thrown when date or time entered in invalid format
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Constructs InvalidDateTimeException object
     */
    public InvalidDateTimeException() {
        super();
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        String error = "OOPS!!! Please enter date & time in this format (yyyy-MM-dd HH:mm)";
        return error;
    }
}
