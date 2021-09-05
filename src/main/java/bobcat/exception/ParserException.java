package bobcat.exception;

/**
 * A BobCatException that is thrown due to errors in the parser
 */
public class ParserException extends BobCatException {
    public ParserException(String errorMessage) {
        super(errorMessage);
    }
}
