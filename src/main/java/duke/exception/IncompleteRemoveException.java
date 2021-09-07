package duke.exception;

/**
 * Representation for incomplete fields of remove exception.
 */
public class IncompleteRemoveException extends DukeException {

    private static String MESSAGE = "Please key in valid number to remove.";

    /**
     * Constructor for IncompleteRemoveException.
     */
    public IncompleteRemoveException() {
        super(MESSAGE);
    }
}
