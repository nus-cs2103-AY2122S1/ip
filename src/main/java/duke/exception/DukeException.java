package duke.exception;

/**
 * Exception class unique to Duke.
 *
 * @author limzk126
 * @version Level-5
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
