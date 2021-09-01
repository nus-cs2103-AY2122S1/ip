package bobcat.exception;

public class InvalidArgumentException extends ParserException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
