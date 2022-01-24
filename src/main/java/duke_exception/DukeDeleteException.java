package duke_exception;

/**
 * Represents a delete exception.
 */
public class DukeDeleteException extends DukeException {
    /**
     * Constructs a DukeDeleteException object.
     */
    public DukeDeleteException() {
        super("OOPS!!! The index of a delete cannot be empty.");
    }
}
