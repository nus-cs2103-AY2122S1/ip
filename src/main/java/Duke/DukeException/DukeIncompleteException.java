package Duke.DukeException;

public class DukeIncompleteException extends DukeException {
    public DukeIncompleteException() {
        super("☹ Cannot lah! Your command is incomplete!\n", Type.INCOMPLETE);
    }
}
