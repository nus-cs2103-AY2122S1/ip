package duke.shared;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super(DukeException.ExceptionCode.INCORRECT_ARGS, "Invalid date(s)");
    }

    public InvalidDateException(String message) {
        super(DukeException.ExceptionCode.INCORRECT_ARGS, message);
    }
}
