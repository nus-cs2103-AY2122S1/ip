package duke.exception;

/**
 * Thrown when the user inputs a todo command without a description.
 */
public class EmptyTodoDescriptionException extends DukeException {
    public EmptyTodoDescriptionException() {
        super("The description of a todo cannot be empty.");
    }
}
