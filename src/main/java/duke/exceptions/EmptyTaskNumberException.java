package duke.exceptions;

public class EmptyTaskNumberException extends DukeException {
    public EmptyTaskNumberException() {
        super("You haven't mentioned the task number! ☹");
    }
}
