package saber.exceptions;

/**
 * A custom exception class to indicate task type is not supported by Saber
 */
public class SaberTaskTypeNotFoundException extends SaberException {

    /**
     * Constructs for SaberTaskTypeNotFoundException
     *
     * @param message the error message
     */
    public SaberTaskTypeNotFoundException(String message) {
        super(message);
    }
}
