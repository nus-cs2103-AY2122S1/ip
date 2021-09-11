package duke.errors;

/**
 * Handles exceptions when deadline format is invalid.
 */
public class InvalidDeadlineException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidDeadlineException(String errorMessage) {
        super("Oops! Deadline formatting is incorrect: " + errorMessage);

    }
}
