package duke.exception;

/**
 * Exception specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message String representation of the exception that is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
