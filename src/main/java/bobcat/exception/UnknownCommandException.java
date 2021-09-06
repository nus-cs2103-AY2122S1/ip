package bobcat.exception;

/**
 * A ParserException that is thrown when a given command is not recognised by the parser
 */
public class UnknownCommandException extends ParserException {
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
