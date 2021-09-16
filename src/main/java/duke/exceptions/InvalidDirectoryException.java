package duke.exceptions;

/**
 * Signals that the specified directory path leading to the storage file does not exist.
 */
public class InvalidDirectoryException extends InvalidStorageFilePathException {
    public InvalidDirectoryException(String errorMessage) {
        super(errorMessage);
    }
}
