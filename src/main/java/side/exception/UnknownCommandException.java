package side.exception;

public class UnknownCommandException extends SideException {
    public UnknownCommandException() {
        super("No such command, what a drag...");
    }
}