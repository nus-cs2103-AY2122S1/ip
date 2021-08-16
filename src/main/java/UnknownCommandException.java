public class UnknownCommandException extends InvalidInputException {
    public UnknownCommandException() {
        super("Command not found.");
    }
}
