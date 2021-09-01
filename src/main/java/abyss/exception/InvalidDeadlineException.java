package abyss.exception;

/**
 * Represents an exception that occurs when user inputs an invalid deadline.
 */
public class InvalidDeadlineException extends InvalidCommandException {
    /**
     * Constructs an InvalidDeadlineException.
     */
    public InvalidDeadlineException() {
        super("Description and date of a 'deadline' task piece "
                  + "cannot be empty.");
    }
}
