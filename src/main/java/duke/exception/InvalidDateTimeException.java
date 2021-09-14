package duke.exception;

/**
 * Thrown when date or time entered in invalid format
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Constructs InvalidDateTimeException object
     *
     * @param msg error message
     */
    public InvalidDateTimeException(String msg) {
        super(msg);
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
