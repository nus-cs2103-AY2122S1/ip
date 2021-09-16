package duke.exception;

/**
 * Represents the incomplete fields of ToDoObject exception.
 */
public class IncompleteToDoException extends DukeException {
    private static String MESSAGE = "OOPS!!! The description of a todo cannot be empty.";

    /**
     * Constructs a IncompleteToDoException object.
     */
    public IncompleteToDoException() {
        super(MESSAGE);
    }
}
