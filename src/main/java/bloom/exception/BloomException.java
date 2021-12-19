package bloom.exception;

/**
 * Represents a general exception of the bot.
 */
public class BloomException extends Exception {

    /**
     * Constructor for a BloomException.
     *
     * @param errorMessage the error message
     */
    public BloomException(String errorMessage) {
        super(errorMessage);
    }
}
