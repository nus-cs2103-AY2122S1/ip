package Duke.DukeException;

public class DukeIncompleteException extends DukeException {
    public DukeIncompleteException(String message, Type type) {
        super(message, Type.INCOMPLETE);
    }
}
