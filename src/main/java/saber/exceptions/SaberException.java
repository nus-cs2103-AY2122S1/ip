package saber.exceptions;

/**
 * A custom exception class to indicate all Saber-related error (during runtime)
 */
public class SaberException extends RuntimeException {

    /**
     * Constructs SaberException
     *
     * @param message the error message
     */
    public SaberException(String message) {
        super(message);
    }
}
