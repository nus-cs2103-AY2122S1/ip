package catobot.exception;

/**
 * EmptyCommandException is thrown when the expected catobot.command details are empty.
 */
public class EmptyCommandException extends BotException {
    /**
     * Creates an EmptyCommandException.
     *
     * @param item The catobot.item where the description is empty.
     */
    public EmptyCommandException(String item) {
        super(String.format("OOPS!!! The description of a / an %s cannot be empty.", item));
    }
}
