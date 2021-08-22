package abyss.exception;

public class InvalidEventException extends InvalidCommandException {
    public InvalidEventException() {
        super("Description and date of a 'event' task piece " +
                "cannot be empty.");
    }
}
