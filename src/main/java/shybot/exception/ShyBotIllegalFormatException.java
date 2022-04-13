package shybot.exception;

/**
 * Throw this when the format of the user input is incorrect.
 */
public class ShyBotIllegalFormatException extends ShyBotException {
    /**
     * Constructs a ShyBotIllegalFormatException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public ShyBotIllegalFormatException(String errorMessage) {
        super(errorMessage);
    }
}
