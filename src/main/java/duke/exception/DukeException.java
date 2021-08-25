package duke.exception;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error detected by Duke:\n" + getMessage();
    }
}
