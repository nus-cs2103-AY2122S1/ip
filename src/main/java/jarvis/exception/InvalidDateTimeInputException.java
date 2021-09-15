package jarvis.exception;

/**
 * Encapsulates an exception when the date time is invalid.
 */
public class InvalidDateTimeInputException extends JarvisException {
    /**
     * Default constructor for InvalidDateTimeInputException.
     *
     * @param errorMessage The error message.
     */
    public InvalidDateTimeInputException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor for InvalidDateTimeInputException.
     *
     * @param field The type of the DateTime (e.g. deadline).
     * @param format The required format.
     */
    public InvalidDateTimeInputException(String field, String format) {
        super(String.format("Please enter your %s using %s format!", field, format));
    }
}
