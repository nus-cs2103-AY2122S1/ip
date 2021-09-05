package pilcrow;

 /**
 * A custom exception to handle invalid exceptions from the user
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
