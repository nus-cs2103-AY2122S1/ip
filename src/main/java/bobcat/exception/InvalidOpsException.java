package bobcat.exception;

/**
 * A LogicException that is thrown when the given command cannot be executed
 */
public class InvalidOpsException extends LogicException {
    public InvalidOpsException(String errorMessage) {
        super(errorMessage);
    }
}
