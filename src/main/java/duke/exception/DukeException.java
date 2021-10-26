package duke.exception;

public class DukeException extends Exception {

    private static final String ERROR_MSG = "I'm sorry, but I don't know what that means :-(";

    public DukeException(String msg) {
        super(msg);
    }

    public DukeException() {
        super(ERROR_MSG);
    }
}
