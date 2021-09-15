package duke.exception;

public class UpdateFileException extends DukeException {
    public UpdateFileException() {
        super("Failed to update file. Please restart the application.");
    }
}
