package duke.exception;

/**
 * Signals that an exception has occurred when initializing a storage.
 */
public class StorageException extends DukeException {
    /**
     * Constructs a StorageException.
     */
    public StorageException() {
        super("Duke is having goldfish memory today so Duke is not going to memorize your task blekk!");
    }
}
