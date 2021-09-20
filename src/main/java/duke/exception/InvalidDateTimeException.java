package duke.exception;

/**
 * Encapsulates the exception when the user enters an invalid date and time format.
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Represents a constructor for the InvalidDateTimeException class.
     */
    public InvalidDateTimeException() {
        super("The format of your date and time is incorrect! It should be <yyyy-mm-dd HHmm>");
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
