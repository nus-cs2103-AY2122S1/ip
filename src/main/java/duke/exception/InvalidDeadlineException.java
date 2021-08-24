package duke.exception;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Please enter deadline in the format yyyy-mm-dd");
    }
}
