package duke.exception;

/**
 * Representation for incomplete fields of Deadline exception.
 */
public class IncompleteDeadlineException extends DukeException {
    private static String MESSAGE = "OOPS!!! The description or date of a deadline is invalid or cannot be read.";

    /**
     * Constructor for IncompleteDeadlineException.
     */
    public IncompleteDeadlineException() {
        super(MESSAGE);
    }
}

