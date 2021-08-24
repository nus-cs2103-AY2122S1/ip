package duke.exception;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("Please input date in yyyy-mm-dd format.");
    }
}
