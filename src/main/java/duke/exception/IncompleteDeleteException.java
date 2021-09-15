package duke.exception;

/**
 * Representation for incomplete fields of delete exception.
 */
public class IncompleteDeleteException extends DukeException {

    private static String MESSAGE = "Please key in valid number to delete.";

    /**
     * Constructor for IncompleteDeleteException.
     */
    public IncompleteDeleteException() {
        super(MESSAGE);
    }
}
