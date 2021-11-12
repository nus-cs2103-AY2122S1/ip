package duke.exception;

/**
 * Exception for when the Folder is not found.
 */
public class FolderNotFoundException extends DukeException {
    public FolderNotFoundException(String message) {
        super(message);
    }
}