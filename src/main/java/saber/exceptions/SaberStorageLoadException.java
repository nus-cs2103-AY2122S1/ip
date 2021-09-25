package saber.exceptions;

/**
 * A custom exception class to indicate loading tasks from storage error
 */
public class SaberStorageLoadException extends SaberException {

    /**
     * Constructs for SaberStorageLoadException
     *
     * @param message the error message
     */
    public SaberStorageLoadException(String message) {
        super(message);
    }
}
