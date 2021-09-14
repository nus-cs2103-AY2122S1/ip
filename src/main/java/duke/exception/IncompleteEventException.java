package duke.exception;

/**
 * Representation for incomplete fields of Event exception.
 */
public class IncompleteEventException extends DukeException {
    private static String MESSAGE = "OOPS!!! The description or date of an event is invalid or cannot be read.";

    /**
     * Constructor for IncompleteEventException.
     */
    public IncompleteEventException() {
        super(MESSAGE);
    }
}
