package duke.exception;

/**
 * Represents an exception caused by Duke being unable to save tasks to save file.
 */
public class CannotSaveFileException extends DukeException {
    /**
     * Constructor for CannotSaveFileException.
     */
    public CannotSaveFileException() {
        super("Failed to save tasks.");
    }
}
