package duke.exception;

/**
 * Signals that there has been an issue with the storage.
 */
public class SaveFileException extends DukeException {
    public SaveFileException(String errMsg) {
        super(errMsg);
    }
}
