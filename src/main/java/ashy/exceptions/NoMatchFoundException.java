package ashy.exceptions;

public class NoMatchFoundException extends AshyException {
    public NoMatchFoundException() {
        super("There are no related tasks in your list! ☹");
    }
}
