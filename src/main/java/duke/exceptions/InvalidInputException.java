package duke.exceptions;

/**
 * Signals that the user input is invalid.
 */
public class InvalidInputException extends DukeException {
    /**
     * @param errorMessage contains the specific error caused by invalid input
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}

