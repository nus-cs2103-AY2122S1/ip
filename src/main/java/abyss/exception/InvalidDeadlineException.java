package abyss.exception;

public class InvalidDeadlineException extends InvalidCommandException {
    public InvalidDeadlineException() {
        super("Description and date of a 'deadline' task piece " +
                "cannot be empty.");
    }
}
