package duke.errors;

/**
 * Handles exceptions when done format is invalid.
 */
public class InvalidDoneException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidDoneException(String errorMessage) {
        super("Oops! Done formatting is incorrect: " + errorMessage);

    }
}
