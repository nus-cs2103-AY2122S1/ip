package saber.exceptions;

/**
 * A custom exception class to indicate time parsing error
 */
public class SaberTimeParserException extends SaberException {

    /**
     * Constructs for SaberTimeParserException
     *
     * @param message the error message
     */
    public SaberTimeParserException(String message) {
        super(message);
    }
}
