package exception;

/**
 * Encapsulates an exception when an input has an invalid datetime format.
 */
public class InvalidDateTimeFormatException extends DukeException {
    /**
     * Instantiates an exception when an input has an invalid datetime format.
     *
     * @param format Required datetime format.
     */
    public InvalidDateTimeFormatException(String dateOrTime, String format) {
        super(String.format("%s should be in the form of <%s>", dateOrTime, format));
    }
}
