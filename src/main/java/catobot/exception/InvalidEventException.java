package catobot.exception;

/**
 * InvalidEventCommandException is thrown when the command is invalid.
 */
public class InvalidEventException extends BotException {
    /**
     * Creates a InvalidDeadlineException.
     */
    public InvalidEventException() {
        super("Oh no, please give me a valid event with date >.<");
    }

}