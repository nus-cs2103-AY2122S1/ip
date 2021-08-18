public class EmptyCommandException extends InvalidCommandException {

    /**
     * Overridden method from InvalidCommandException. Constructor
     */
    public EmptyCommandException() {
        super("Input is empty! Please enter something into the chat");
    }
}
