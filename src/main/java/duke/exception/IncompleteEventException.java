package duke.exception;

/**
 * Represents the incomplete fields of Event exception.
 */
public class IncompleteEventException extends DukeException {
    /**
     * Represents the error message.
     */
    private static String MESSAGE = "OOPS!!! The description or date of an event is invalid or cannot be read.";

    /**
     * Constructs a IncompleteEventException object.
     */
    public IncompleteEventException() {
        super(MESSAGE);
    }
}
