package Duke.DukeException;

public class DukeOutOfBoundException extends DukeException {
    public DukeOutOfBoundException() {
        super("The index is out of range bro\n", Type.OUT_OF_BOUND);
    }
}
