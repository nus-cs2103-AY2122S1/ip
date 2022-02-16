package duke.exception;

/**
 * Exceptions thrown by Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException class.
     *
     * @param message error message.
     */
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
