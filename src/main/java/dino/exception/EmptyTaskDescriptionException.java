package dino.exception;

public class EmptyTaskDescriptionException extends DinoException {
    public EmptyTaskDescriptionException() {
        super("The description of a task cannot be empty.");
    }
}
