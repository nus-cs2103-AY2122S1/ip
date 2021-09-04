package duke.exception;

/**
 * Represents a DukeException that is thrown when a valid command has information
 * that is invalid or in the wrong format.
 */
public class InvalidParamException extends DukeException {

    /**
     * Constructs an InvalidParamException.
     *
     * @param errorMessage Error message describing this Exception.
     */
    public InvalidParamException(String errorMessage) {
        super(errorMessage);
    }
}
