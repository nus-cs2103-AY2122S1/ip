public class EmptyCommandException extends InvalidCommandException {
    public EmptyCommandException() {
        super("Input is empty! Please enter something into the chat");
    }
}
