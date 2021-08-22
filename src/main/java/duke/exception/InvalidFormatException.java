package duke.exception;

public class InvalidFormatException extends DukeException {
    public InvalidFormatException(String format) {
        super("Invalid format! Try " + format);
    }
}
