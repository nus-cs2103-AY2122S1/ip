package exception;

/**
 * BotException are possible exceptions thrown by Catobot.
 */
public class BotException extends Exception {
    /**
     * Create a BotException.
     * @param errorMessage The error message.
     */
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Get the message of the BotException
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
