package shybot.exception;

/**
 * Throw this when data format is incorrect ie. parser fails to parse the data into a list.
 */
public class ShyBotParseException extends ShyBotException {
    /**
     * Constructs a ShyBotException with no detailed message.
     */
    public ShyBotParseException() {
    }

    /**
     * Constructs a ShyBotParseException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public ShyBotParseException(String errorMessage) {
        super(errorMessage);
    }
}
