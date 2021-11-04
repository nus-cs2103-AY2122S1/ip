package duke.exception;

/** Used when the task description is empty. */
public class EmptyDescriptionException extends InvalidInputException {
    public EmptyDescriptionException() {
        super("Missing task description");
    }
}
