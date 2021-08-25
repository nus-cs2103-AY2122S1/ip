package Duke.DukeException;

public class DukeInvalidFormatException extends DukeException {
    public DukeInvalidFormatException(String message, Type type) {
        super(message, Type.INVALID_FORMAT);
    }
}
