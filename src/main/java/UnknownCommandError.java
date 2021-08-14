public class UnknownCommandError extends InvalidCommandException {
    public UnknownCommandError(String message) {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
