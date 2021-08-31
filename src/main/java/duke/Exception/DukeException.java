package duke.exception;

/**
 * A class that represents exceptions thrown when using Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the given error message.
     * @param message The given error message.
     */
    public DukeException(String message) {
        super(message);
    }
}

