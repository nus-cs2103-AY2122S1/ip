package ashy.exceptions;

public class EmptyTaskDescriptionException extends AshyException {
    public EmptyTaskDescriptionException() {
        super("Oh no! Please provide a task description! ☹");
    }
}
