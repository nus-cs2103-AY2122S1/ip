package bobcat.exception;

/**
 * A ParserException that is thrown when number of arguments to the command do not match
 * the expected number of arguments
 */
public class CommandArityException extends ParserException {
    public CommandArityException(String errorMessage) {
        super(errorMessage);
    }
}
