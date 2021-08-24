package catobot.exception;

/**
 * Represents the exception when the command is invalid.
 */
public class InvalidCommandException extends BotException {
    /**
     * Constructor for InvalidCommandException.
     */
    public InvalidCommandException() {
        super("Meow I don't understand this command >.<");
    }

}
