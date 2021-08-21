package exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException(String format) {
        super(String.format("Please enter a valid datetime in the form of '%s'", format));
    }
}
