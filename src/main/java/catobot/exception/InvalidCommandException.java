package catobot.exception;

/**
 * InvalidCommandException is thrown when the catobot.command is invalid.
 */
public class InvalidCommandException extends BotException {
    /**
     * Creates a default InvalidCommandException.
     */
    public InvalidCommandException() {
        super("Meow I don't understand this command >.<");
    }

}
