package duke;

public class EmptyDeadlineException extends DukeException {
    public EmptyDeadlineException(String err) {
        super(err);
    }

    public EmptyDeadlineException() {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}
