package shybot.exception;

/**
 * Throw this when the user input is erroneous or does not abide by the rule stipulated.
 */
public class ShyBotException extends Exception {
    /**
     * Constructs a ShyBotException with no detailed message.
     */
    public ShyBotException() {
    }

    /**
     * Constructs a ShyBotException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public ShyBotException(String errorMessage) {
        super(errorMessage);
    }


}


