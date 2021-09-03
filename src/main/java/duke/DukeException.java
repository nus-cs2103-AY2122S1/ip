package duke;

public class DukeException extends RuntimeException {
    private String msg;

    public DukeException(String message) {
        msg = message;
    }

    @Override
    public String toString() {
        return msg;
    }
}
