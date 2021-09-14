package duke.exception;

public class UnknownCommandDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public UnknownCommandDukeException() {
        super(ERROR_MESSAGE);
    }
}
