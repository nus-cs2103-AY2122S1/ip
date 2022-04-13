package duke.exception;

/**
 * Represents an exception encountered when running Duke.
 */
public class DukeException extends Exception {

    /**
     * Initialises a new instance of DukeException.
     *
     * @param message The given error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
