package saber.exceptions;

/**
 * A custom exception class to indicate that the command given by the user is unsupported by Saber
 */
public class SaberCommandNotFoundException extends SaberException {

    /**
     * Constructs for SaberCommandNotFoundException
     *
     * @param message the error message
     */
    public SaberCommandNotFoundException(String message) {
        super(message);
    }
}
