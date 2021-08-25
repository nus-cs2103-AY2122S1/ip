package duke.exceptions;

/**
 * Signals that there are some inputs which were given and Duke is not able to comprehend them
 */
public class InvalidInputException extends DukeException {

    /**
     * @param errorMessage will indicate the type of invalid input given
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
