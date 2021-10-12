package duke.exception;

/**
 * An exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Public constructor to create a DukeException.
     *
     * @param message Error message to be shown.
     */
    public DukeException(String message) {
        super(message);
    }
}
