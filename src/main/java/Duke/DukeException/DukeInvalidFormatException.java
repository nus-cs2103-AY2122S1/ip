package Duke.DukeException;

public class DukeInvalidFormatException extends DukeException {
    public DukeInvalidFormatException() {
        super("Try to enter your date in the YYYY-MM-DD format!\n", Type.INVALID_FORMAT);
    }
}
