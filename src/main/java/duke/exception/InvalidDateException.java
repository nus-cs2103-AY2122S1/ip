package duke.exception;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Invalid date format, should be [dd/MM/yy] [HHmm]");
    }
}
