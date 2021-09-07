package duke.exception;

/**
 * Represents an exception that can occur when running Duke.
 *
 * @author botr99
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the given message.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
