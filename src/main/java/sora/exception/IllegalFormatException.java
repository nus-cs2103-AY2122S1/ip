package sora.exception;

/**
 * Throws when user's input does not follow the given format.
 *
 * @author Zhang Shi Chen
 */
public class IllegalFormatException extends SoraException {
    public IllegalFormatException(String format) {
        super("Please follow this format:\n  " + format);
    }
}
