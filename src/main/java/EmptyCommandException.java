public class EmptyCommandException extends InvalidInputException {
    public EmptyCommandException() {
        super("Command input cannot be empty!");
    }
}
