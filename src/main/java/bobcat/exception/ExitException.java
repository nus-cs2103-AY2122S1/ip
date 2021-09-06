package bobcat.exception;

public class ExitException extends LogicException {
    public ExitException(String errorMessage) {
        super(errorMessage);
    }
}
