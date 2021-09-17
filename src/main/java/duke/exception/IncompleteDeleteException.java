package duke.exception;

/**
 * Represents the incomplete fields of delete exception.
 */
public class IncompleteDeleteException extends DukeException {
    /**
     * Represents the error message.
     */
    private static String MESSAGE = "Please key in valid number to delete.";

    /**
     * Constructs a IncompleteDeleteException object.
     */
    public IncompleteDeleteException() {
        super(MESSAGE);
    }
}
