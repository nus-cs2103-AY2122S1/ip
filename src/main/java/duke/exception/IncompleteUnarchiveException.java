package duke.exception;
/**
 * Represents exception where Unarchive command has missing fields.
 */
public class IncompleteUnarchiveException extends DukeException {
    private static String MESSAGE = "Please key in valid index to unarchive.";

    /**
     * Constructor for IncompleteDeadlineException.
     */
    public IncompleteUnarchiveException() {
        super(MESSAGE);
    }
}
