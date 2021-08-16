package exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Sorry, I don't understand that command.");
    }
}
