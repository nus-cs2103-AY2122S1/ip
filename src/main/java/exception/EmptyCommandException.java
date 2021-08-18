package exception;

/**
 * EmptyCommandException is thrown when the expected command details are empty.
 */
public class EmptyCommandException extends BotException {
    /**
     * Create an EmptyCommandException.
     * @param item The item where the description is empty.
     */
    public EmptyCommandException(String item) {
        super(String.format("OOPS!!! The description of a / an %s cannot be empty.", item));
    }
}
