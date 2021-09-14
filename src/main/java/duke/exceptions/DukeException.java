package duke.exceptions;

/**
 * Exception related to Duke program.
 */
public class DukeException extends Exception {
    
    /**
     * Constructor class for DukeException.
     *
     * @param message Error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}
