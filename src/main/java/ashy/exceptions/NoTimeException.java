package ashy.exceptions;

public class NoTimeException extends AshyException {
    public NoTimeException() {
        super("Oh no! Please provide a timeline for your task! ☹");
    }
}
