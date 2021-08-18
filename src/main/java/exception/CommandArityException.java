package exception;

public class CommandArityException extends ParserException {
    public CommandArityException(String errorMessage) {
        super(errorMessage);
    }
}
