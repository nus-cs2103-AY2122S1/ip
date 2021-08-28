package duke.exception;

/**
 * DukeException is a custom error created for duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}
