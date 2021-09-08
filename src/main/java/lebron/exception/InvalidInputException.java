package lebron.exception;

/**
 * Represents the exception when an invalid input has been provided.
 */
public class InvalidInputException extends LebronException {
    /**
     * Creates an InvalidInputException with the given error message.
     *
     * @param errorMessage the error message.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
