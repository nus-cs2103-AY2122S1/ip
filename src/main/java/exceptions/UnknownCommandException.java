package exceptions;

public class UnknownCommandException extends AuguryException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
