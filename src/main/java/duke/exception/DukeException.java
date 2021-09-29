package duke.exception;

/**
 * Exception class that is unique to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     *
     * @param message Error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}
