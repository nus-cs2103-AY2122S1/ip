package duke_exception;

/**
 * Represents a deadline exception.
 */
public class DukeDeadlineException extends DukeException {
    /**
     * Constructs a DukeDeadlineException object.
     */
    public DukeDeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
}
