package exception;

/**
 * InvalidCommandException is thrown when the command is invalid.
 */
public class InvalidCommandException extends BotException {
    /**
     * Create a default InvalidCommandException.
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Create a InvalidCommandException.
     * @param errorMessage The customized error message.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
