package ashy.exceptions;

public class EmptyTaskNumberException extends AshyException {
    public EmptyTaskNumberException() {
        super("You haven't mentioned the task number! ☹");
    }
}
