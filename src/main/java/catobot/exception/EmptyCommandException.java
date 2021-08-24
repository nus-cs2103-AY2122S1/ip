package catobot.exception;

/**
 * Represents exceptions when the command details are empty.
 */
public class EmptyCommandException extends BotException {
    /**
     * Constructor for EmptyCommandException.
     *
     * @param item The place where the description is empty.
     */
    public EmptyCommandException(String item) {
        super(String.format("OOPS!!! The description of a / an %s cannot be empty.", item));
    }
}
