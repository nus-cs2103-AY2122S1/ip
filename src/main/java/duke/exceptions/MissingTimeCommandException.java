package duke.exceptions;

/** Throws error for missing /at or /by commands */
public class MissingTimeCommandException extends DukeException {
    public MissingTimeCommandException(String message) {
        super(message);
    }
}