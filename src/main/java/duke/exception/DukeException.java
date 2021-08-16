package duke.exception;

/**
 * Exception class unique that is unique to Duke.
 *
 * @author limzk126
 * @version Level-6
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
