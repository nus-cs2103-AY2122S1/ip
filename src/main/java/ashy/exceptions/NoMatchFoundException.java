package ashy.exceptions;

public class NoMatchFoundException extends AshyException {
    public NoMatchFoundException() {
        super("Oh no! There are no related tasks in your list! ☹");
    }
}
