package catobot.exception;

/**
 * BotException are possible exceptions thrown by catobot.Catobot.
 */
public class BotException extends Exception {
    /**
     * Creates a BotException.
     *
     * @param errorMessage The error message.
     */
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Gets the message of the BotException.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
