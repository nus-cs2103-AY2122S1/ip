package ashy.exceptions;

public class NoTimeException extends AshyException {
    public NoTimeException() {
        super("Please provide a timeline for your task! ☹");
    }
}
