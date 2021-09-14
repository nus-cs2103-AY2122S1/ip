package duke.exception;

public class MissingParameterDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! Parameter for \"%s\" is missing";
    public MissingParameterDukeException(String command) {
        super(String.format(ERROR_MESSAGE, command));
    }
}
