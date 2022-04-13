package shybot.exception;

/**
 * Throw this when data format is incorrect ie. parser fails to parse the data into a list.
 */
public class ShyBotIoException extends ShyBotException {
    /**
     * Constructs a ShyBotException with no detailed message.
     */
    public ShyBotIoException() {
    }

    /**
     * Constructs a ShyBotIoException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public ShyBotIoException(String errorMessage) {
        super(errorMessage);
    }
}
