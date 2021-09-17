package duke.exception;

/**
 * Exception representing an issue with Duke file storage.
 */
public class StorageException extends DukeException {
    public StorageException(String message) {
        super(message);
    }
}
