package myjournal.exception;

/**
 * Creates invalid command exceptions.
 *
 * @author Felissa Faustine.
 */
public class InvalidCommandException extends MyJournalException {

    /**
     * Constructs InvalidCommandException objects.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Gets the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "InvalidCommandException: " + getMessage();
    }
}
