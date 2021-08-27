package duke.data.exception;

/**
 * Signals an error from Duke.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super("! " + message + " !");
    }
}
