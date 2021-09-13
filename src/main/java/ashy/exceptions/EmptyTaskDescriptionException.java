package ashy.exceptions;

public class EmptyTaskDescriptionException extends AshyException {
    public EmptyTaskDescriptionException() {
        super("Please provide a task description! ☹");
    }
}
