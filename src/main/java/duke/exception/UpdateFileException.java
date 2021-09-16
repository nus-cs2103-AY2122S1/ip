package duke.exception;

/** Exception to be thrown when a file is unable to be updated */
public class UpdateFileException extends DukeException {
    /**
     * Constructor for an UpdateFileException
     */
    public UpdateFileException() {
        super("Failed to update file. Please restart the application.");
    }
}
