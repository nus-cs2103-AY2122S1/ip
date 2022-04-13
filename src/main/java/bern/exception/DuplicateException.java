package bern.exception;

public class DuplicateException extends BernException {
    public DuplicateException(String command) {
        super("This task has been created before:\n" + command);
    }
}
