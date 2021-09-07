package duke_exception;

/**
 * Represents a todo exception.
 */
public class DukeTodoException extends DukeException {
    /**
     * Constructs a DukeTodoException object.
     */
    public DukeTodoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
