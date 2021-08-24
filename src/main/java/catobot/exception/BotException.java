package catobot.exception;

/**
 * Represents exceptions thrown by Catobot classes.
 */
public class BotException extends Exception {
    /**
     * Constructor for BotException.
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
