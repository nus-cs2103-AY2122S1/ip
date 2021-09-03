package exception;

/**
 * Encapsulates an exception when an input has an invalid datetime format.
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Instantiates an exception when an input has an invalid datetime format.
     *
     * @param format Required datetime format.
     */
    public InvalidDateTimeException(String format) {
        super(String.format("Please enter a valid datetime in the form of '%s'", format));
    }
}
