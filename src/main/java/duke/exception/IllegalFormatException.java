package duke.exception;

public class IllegalFormatException extends DukeException {
    public IllegalFormatException(String format) {
        super("Please follow this format:\n  " + format);
    }
}
