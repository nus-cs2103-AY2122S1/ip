package duke.exception;

public class DukeException extends RuntimeException {
    private String prefix = "OOPS!!! ";
    private String msg;

    public DukeException(String message) {
        msg = prefix + message;
    }

    @Override
    public String toString() {
        return msg;
    }
}
