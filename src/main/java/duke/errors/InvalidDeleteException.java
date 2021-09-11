package duke.errors;

/**
 * Handles exceptions when delete format is invalid.
 */
public class InvalidDeleteException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidDeleteException(String errorMessage) {
        super("Oops! Delete formatting is incorrect: " + errorMessage);

    }
}
