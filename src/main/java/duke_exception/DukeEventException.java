package duke_exception;

/**
 * Represents an event exception.
 */
public class DukeEventException extends DukeException {
    /**
     * Constructs a DukeEventException object.
     */
    public DukeEventException() {
        super("OOPS!!! The description of a event cannot be empty.");
    }
}
