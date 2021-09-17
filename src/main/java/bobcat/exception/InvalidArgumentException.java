package bobcat.exception;

/**
 * A ParserException that is thrown when the arguments to a command is not understandable by the parser
 */
public class InvalidArgumentException extends ParserException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
