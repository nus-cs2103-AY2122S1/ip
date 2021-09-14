package duke.exception;

public class FolderNotFoundDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! \"%s\" folder does not exist.";
    public FolderNotFoundDukeException(String folder) {
        super(String.format(ERROR_MESSAGE, folder));
    }
}
