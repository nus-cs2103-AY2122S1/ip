package duke.exception;

public class BadFileDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! File appears to be corrupted.";
    public BadFileDukeException() {
        super(ERROR_MESSAGE);
    }
}
