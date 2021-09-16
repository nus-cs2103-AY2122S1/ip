package duke.exceptions;

/**
 * Signifies that the storage file does not end with a .txt format.
 */
public class InvalidStorageFilePathException extends Exception {
    public InvalidStorageFilePathException(String errorMessage) {
        super(errorMessage);
    }
}
