package duke.exception;

/**
 * Represents exception where archive command has missing fields.
 */
public class IncompleteArchiveException extends DukeException {
    /**
     * Represents the error message.
     */
    private static String MESSAGE = "Please key in valid index to archive.";

    /**
     * Constructs a IncompleteArchiveException object.
     */
    public IncompleteArchiveException() {
        super(MESSAGE);
    }
}
