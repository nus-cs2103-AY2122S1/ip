package duke.exception;

/**
 * Signals that an invalid input has been provided.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
