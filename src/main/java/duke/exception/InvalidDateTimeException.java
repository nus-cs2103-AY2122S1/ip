package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please enter a date of the format yyyy-mm-dd!");
    }
}