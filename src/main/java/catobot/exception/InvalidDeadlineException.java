package catobot.exception;

/**
 * Represents exception when creating deadline with invalid input.
 */
public class InvalidDeadlineException extends BotException {
    /**
     * Constructor for InvalidDeadlineException.
     */
    public InvalidDeadlineException() {
        super("Don't cheat me, give me a due time so I can watch you >.<");
    }

}
