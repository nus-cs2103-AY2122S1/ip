package duke.exception;

public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("The description of a deadline cannot be empty.");
    }
    public DeadlineException(String str) {
        super(str);
    }
}
