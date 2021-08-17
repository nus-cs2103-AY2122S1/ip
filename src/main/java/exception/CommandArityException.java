package exception;

public class CommandArityException extends RuntimeException {
    public CommandArityException(String errorMessage) {
        super(errorMessage);
    }
}
