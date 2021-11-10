package duke.exception;

/**
 * An exception class that represents any Duke-related issues.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a {@code DukeException}.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
