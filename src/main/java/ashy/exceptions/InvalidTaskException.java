package ashy.exceptions;

public class InvalidTaskException extends AshyException {
    public InvalidTaskException() {
        super("There is no such task in your to-do list! ☹");
    }
}
