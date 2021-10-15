package me.yukun99.ip.exceptions;

/**
 * Exceptions resulting from user input errors when using our chat bot.
 */
public class HelpBotException extends Exception {
    /**
     * Constructor for a HelpBotException instance not directly caused by other errors.
     */
    public HelpBotException() {
        super("Exception in Help Bot:");
    }

    /**
     * Constructor for a HelpBotException instance caused by other errors.
     *
     * @param error Error that caused this exception.
     */
    public HelpBotException(Throwable error) {
        super("Exception in Help Bot:", error);
    }

    /**
     * Returns a string representation of the HelpBotException instance.
     *
     * @return The string representation of the HelpBotException instance.
     */
    public String toString() {
        return super.getMessage();
    }
}
