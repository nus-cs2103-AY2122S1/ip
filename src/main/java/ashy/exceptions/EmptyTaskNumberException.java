package ashy.exceptions;

public class EmptyTaskNumberException extends AshyException {
    public EmptyTaskNumberException() {
        super("Oh no! You haven't mentioned the task number! ☹");
    }
}
