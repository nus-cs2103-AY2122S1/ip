package exceptions;

/**
 * Exception for invalid task being referred to
 */
public class InvalidTaskException extends RuntimeException {

    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
    }

}
