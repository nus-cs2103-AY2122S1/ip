package duke.exception;

/**
 * InvalidParameterDukeException class.
 * Used to represent an invalid parameter for any command.
 */
public class InvalidParameterDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! The parameter is invalid";

    /**
     * InvalidParameterDukeException constructor.
     */
    public InvalidParameterDukeException() {
        super(ERROR_MESSAGE);
    }
}
