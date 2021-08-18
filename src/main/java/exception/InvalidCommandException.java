package exception;

public class InvalidCommandException extends BotException {
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public InvalidCommandException(String message) {
        super(message);
    }
}
