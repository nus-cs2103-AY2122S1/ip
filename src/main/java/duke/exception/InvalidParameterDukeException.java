package duke.exception;

public class InvalidParameterDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! The parameter is invalid";
    public InvalidParameterDukeException() {
        super(ERROR_MESSAGE);
    }
}
