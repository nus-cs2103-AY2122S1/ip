package duke.exceptions;

public class DukeIncompleteException extends DukeException {
    public DukeIncompleteException() {
        super("☹ Cannot what! Your command is incomplete!\n");
    }
}
