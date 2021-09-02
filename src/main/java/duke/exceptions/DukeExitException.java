package duke.exceptions;

/**
 * Exception raised when unable to read save file.
 */
public class DukeExitException extends DukeException {
    /**
     * Constructor for DukeReadSaveException.
     *
     * @param message error message to return.
     */
    public DukeExitException(String message) {
        super(message);
    }
}
