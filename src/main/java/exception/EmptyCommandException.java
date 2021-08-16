package exception;

public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("You left the field blank!");
    }
}
