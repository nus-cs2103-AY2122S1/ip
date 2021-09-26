package duke.exception;

/**
 * Signals that an exception has occurred when initializing a storage.
 */
public class StorageException extends DukeException {
    /**
     * Constructs a StorageException.
     */
    public StorageException() {
        super("Error creating storage.");
    }
}
