package duke.exception;

/**
 * MissingParameterDukeException class.
 * Used to represent missing parameters for a command.
 */
public class MissingParameterDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! Parameter for \"%s\" is missing";

    /**
     * MissingParameterDukeException constructor.
     */
    public MissingParameterDukeException(String command) {
        super(String.format(ERROR_MESSAGE, command));
    }
}
