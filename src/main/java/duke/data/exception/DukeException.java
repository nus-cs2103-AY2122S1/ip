package duke.data.exception;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super("! " + message + " !");
    }
}
