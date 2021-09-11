package duke.exception;

/**
 * Class that encapsulates an invalid format Duke-specific exception.
 */
public class InvalidFormatException extends DukeException {

    /**
     * Constructs a InvalidDateTimeException instance.
     *
     * @param format Required format
     */
    public InvalidFormatException(String format) {
        super("Invalid format! Try " + format);
    }
}
