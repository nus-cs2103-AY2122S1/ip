package duke;

public class UnsupportedOperationException extends DukeException {
    public UnsupportedOperationException() {
        super("No such operation supported");
    }
}
