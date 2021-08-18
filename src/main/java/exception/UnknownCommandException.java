package exception;

public class UnknownCommandException extends ParserException {
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
