package duke.exception;

/**
 * Represents exception where Unarchive command has missing fields.
 */
public class IncompleteUnarchiveException extends DukeException {
    /**
     * Represents the error message.
     */
    private static String MESSAGE = "Please key in valid index to unarchive.";

    /**
     * Constructs a IncompleteUnarchiveException object.
     */
    public IncompleteUnarchiveException() {
        super(MESSAGE);
    }
}
