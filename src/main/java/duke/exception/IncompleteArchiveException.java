package duke.exception;

public class IncompleteArchiveException extends DukeException {
    private static String MESSAGE = "Please key in valid index to archive.";

    /**
     * Constructor for IncompleteDeadlineException.
     */
    public IncompleteArchiveException() {
        super(MESSAGE);
    }
}
