package duke.exception;

/**
 * Represents a DukeException that is thrown when the input for adding a Task into a TaskList has information that is
 * invalid or in the wrong format.
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
