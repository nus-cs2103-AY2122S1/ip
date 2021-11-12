package duke;

/**
 * Empty duke.Command exception
 */
public class EmptyCommandException extends InvalidCommandException {
    /**
     * Overridden method from duke.InvalidCommandException. Constructor
     */
    public EmptyCommandException() {
        super("Input is empty! Please enter something into the chat");
    }
}
