package duke.exception;

/**
 * Represents an exception caused by Duke being unable to read save file.
 */
public class CannotReadFileException extends DukeException {
    /**
     * Constructor for CannotReadFileException.
     */
    public CannotReadFileException() {
        super("Failed to read tasks from save file.");
    }
}
