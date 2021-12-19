package duke.exception;

/**
 * Exception when a save file is corrupted.
 */
public class DukeCorruptedSaveException extends Exception {

    /**
     * Constructor for the duke.exception.
     *
     * @param message Exception message.
     */
    public DukeCorruptedSaveException(String message) {
        super(message);
    }
}
