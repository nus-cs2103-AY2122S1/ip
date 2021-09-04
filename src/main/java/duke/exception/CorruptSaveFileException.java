package duke.exception;

/**
 * Represents an exception caused by Duke being unable to parse a saved Task.
 */
public class CorruptSaveFileException extends DukeException {
    /**
     * Constructor for CorruptSaveFileException.
     */
    public CorruptSaveFileException() {
        super("Save files corrupted. Failed to read tasks from save file.");
    }
}
