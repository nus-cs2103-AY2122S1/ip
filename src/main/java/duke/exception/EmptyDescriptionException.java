package duke.exception;

public class EmptyDescriptionException extends InvalidInputException {
    public EmptyDescriptionException() {
        super("Missing task description");
    }
}
