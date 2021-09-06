package bobcat.exception;

/**
 * RuntimeException for BobCat. All unchecked exceptions in BobCat should throw an exception that extends
 * BobCatException
 */
public class BobCatException extends RuntimeException {
    public BobCatException(String errorMessage) {
        super(errorMessage);
    }
}
