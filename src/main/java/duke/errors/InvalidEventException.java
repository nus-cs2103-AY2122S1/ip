package duke.errors;

/**
 * Handles exceptions when event format is invalid.
 */
public class InvalidEventException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidEventException(String errorMessage) {
        super("Oops! Event formatting is incorrect: " + errorMessage);

    }
}
