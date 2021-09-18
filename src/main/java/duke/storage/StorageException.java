package duke.storage;

/**
 * Signals an error for storing or loading tasks from disk.
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}
