package duke.exception;

/**
 * Thrown when the user inputs a command in an invalid format.
 */
public class InvalidFormatException extends DukeException {
    public InvalidFormatException(String format) {
        super("Invalid format: " + format);
    }
}
