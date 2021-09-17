package duke.exception;

public class MissingTaskNumException extends DukeException {

    private static final String ERROR_MSG = "you did not specify a task number to ";

    public MissingTaskNumException(String command) {
        super(ERROR_MSG + command + "!");
    }
}
