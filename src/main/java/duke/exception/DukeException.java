package duke.exception;

/**
 * Exceptions thrown by Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
