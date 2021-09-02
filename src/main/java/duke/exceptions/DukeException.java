package duke.exceptions;

/**
 * Encapsulates error handling for duke.Duke.
 *
 * @author Owen Tan
 * @version duke.Duke Level-9
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
