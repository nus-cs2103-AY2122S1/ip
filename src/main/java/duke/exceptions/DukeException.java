package duke.exceptions;

/**
 * Encapsulates error handling for Duke.
 *
 * @author Owen Tan
 * @version Duke Level-9
 */
public class DukeException extends Exception {
    /**
     * Public constructor for DukeException.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
