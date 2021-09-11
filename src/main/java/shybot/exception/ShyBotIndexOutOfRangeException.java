package shybot.exception;

/**
 * Throw this when index input is out of range.
 */
public class ShyBotIndexOutOfRangeException extends ShyBotException {
    /**
     * Constructs a ShyBotIndexOutOfRangeException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public ShyBotIndexOutOfRangeException(String errorMessage) {
        super(errorMessage);
    }
}
