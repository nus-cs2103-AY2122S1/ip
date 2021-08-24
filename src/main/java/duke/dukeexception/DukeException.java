package duke.dukeexception;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(Exception e) {
        super(e);
    }
}
