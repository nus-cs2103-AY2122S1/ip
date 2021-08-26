package saber.exceptions;

/**
 * A custom exception class to indicate time parsing error
 */
public class SaberTimeParserException extends SaberException {

    /**
     * A constructor for SaberTimeParserException
     * @param message the error message
     */
    public SaberTimeParserException(String message) {
        super(message);
    }
}
