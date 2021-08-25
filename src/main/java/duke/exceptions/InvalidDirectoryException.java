package duke.exceptions;

public class InvalidDirectoryException extends InvalidStorageFilePathException {
    public InvalidDirectoryException(String errorMessage) {
        super(errorMessage);
    }
}
