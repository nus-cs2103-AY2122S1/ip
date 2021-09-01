package abyss.exception;

/**
 * Represents an exception that occurs when user inputs an invalid event.
 */
public class InvalidEventException extends InvalidCommandException {
    /**
     * Constructs an InvalidEventException.
     */
    public InvalidEventException() {
        super("Description and date of a 'event' task piece "
                  + "cannot be empty.");
    }
}
