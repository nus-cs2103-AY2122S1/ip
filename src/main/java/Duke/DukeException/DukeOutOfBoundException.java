package Duke.DukeException;

public class DukeOutOfBoundException extends DukeException {
    public DukeOutOfBoundException(String message, Type type) {
        super(message, Type.OUT_OF_BOUND);
    }
}
