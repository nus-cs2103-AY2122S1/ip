package duke.exceptions;

/**
 * General Exception for Duke related issues.
 */
public class DukeException extends Exception {
    /**
     * Constructor for Duke Exception.
     *
     * @param message error message to return.
     */
    public DukeException(String message) {
        super(message);
    }
}
