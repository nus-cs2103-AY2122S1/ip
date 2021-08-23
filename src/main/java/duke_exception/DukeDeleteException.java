package duke_exception;
public class DukeDeleteException extends DukeException {
    public DukeDeleteException() {
        super("OOPS!!! The index of a delete cannot be empty.");
    }
}
