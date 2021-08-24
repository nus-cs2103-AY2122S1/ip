package catobot.exception;

/**
 * InvalidDeadlineException is thrown when the command is invalid.
 */
public class InvalidDeadlineException extends BotException {
    /**
     * Creates a InvalidDeadlineException.
     */
    public InvalidDeadlineException() {
        super("Don't cheat me, give me a due time so I can watch you >.<");
    }

}
