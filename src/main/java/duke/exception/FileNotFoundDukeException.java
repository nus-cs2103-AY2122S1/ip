package duke.exception;

public class FileNotFoundDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! File not found.";
    public FileNotFoundDukeException() {
        super(ERROR_MESSAGE);
    }
}
