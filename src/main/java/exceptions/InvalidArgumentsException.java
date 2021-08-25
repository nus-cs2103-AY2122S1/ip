package exceptions;

/**
 * Exception for invalid arguments
 */
public class InvalidArgumentsException extends RuntimeException {

    public InvalidArgumentsException(String errorMessage) {
        super(errorMessage);
    }

}
