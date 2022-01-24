package duke_exception;

/**
 * Represents a general exception.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException.
     *
     * @param message exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
