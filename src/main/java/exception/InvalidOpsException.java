package exception;

public class InvalidOpsException extends LogicException{
    public InvalidOpsException(String errorMessage) {
        super(errorMessage);
    }
}
