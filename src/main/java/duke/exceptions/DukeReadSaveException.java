package duke.exceptions;

/**
 * Exception raised when unable to read save file.
 */
public class DukeReadSaveException extends DukeException {
    /**
     * Constructor for DukeReadSaveException.
     *
     * @param message error message to return.
     */
    public DukeReadSaveException(String message) {
        super(message);
    }
}
