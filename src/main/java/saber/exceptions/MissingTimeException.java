package saber.exceptions;

/**
 * A custom exception class to indicate missing time error
 */
public class MissingTimeException extends SaberException {

    /**
     * A constructor for MissingTimeException
     * @param message the error message
     */
    public MissingTimeException(String message) {
        super(message);
    }
}
