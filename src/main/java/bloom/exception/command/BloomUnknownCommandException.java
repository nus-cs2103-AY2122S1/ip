package bloom.exception.command;

import bloom.exception.BloomException;

/**
 * Represents an exception when users input unlisted commands.
 */
public class BloomUnknownCommandException extends BloomException {

    /**
     * Constructor for a BloomUnknownCommandException.
     *
     * @param errorMessage the error message
     */
    public BloomUnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
