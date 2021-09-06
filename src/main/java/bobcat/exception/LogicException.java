package bobcat.exception;

/**
 * A BobCatException that is thrown due to errors during execution of a given command
 */
public class LogicException extends BobCatException {
    public LogicException(String errorMessage) {
        super(errorMessage);
    }
}
