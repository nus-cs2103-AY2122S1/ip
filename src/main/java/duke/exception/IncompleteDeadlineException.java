package duke.exception;

/**
 * Represents the incomplete fields of Deadline exception.
 */
public class IncompleteDeadlineException extends DukeException {
    /**
     * Represents the error message.
     */
    private static String MESSAGE = "OOPS!!! The description or date of a deadline is invalid or cannot be read.";

    /**
     * Constructs a IncompleteDeadlineException object.
     */
    public IncompleteDeadlineException() {
        super(MESSAGE);
    }
}

