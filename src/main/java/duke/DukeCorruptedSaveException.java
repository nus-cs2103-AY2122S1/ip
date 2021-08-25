package duke;

/**
 * Exception when a save file is corrupted.
 */
public class DukeCorruptedSaveException extends Exception {

    /**
     * Constructor for the exception.
     * 
     * @param message Exception message.
     */
    public DukeCorruptedSaveException(String message) {
        super(message);
    }
}
