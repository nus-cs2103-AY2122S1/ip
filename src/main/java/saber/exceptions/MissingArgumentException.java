package saber.exceptions;

/**
 * A custom exception class to indicate missing argument error
 */
public class MissingArgumentException extends SaberException {

    /**
     * Constructs for MissingArgumentException
     *
     * @param message the error message
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}
