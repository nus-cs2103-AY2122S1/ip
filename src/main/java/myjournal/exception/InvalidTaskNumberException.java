package myjournal.exception;

/**
 * Creates invalid task number exceptions.
 *
 * @author Felissa Faustine.
 */
public class InvalidTaskNumberException extends MyJournalException {

    /**
     * Constructs the InvalidTaskNumberException objects.
     *
     * @param message The error message.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }

    /**
     * Gets the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "InvalidTaskNumberException: " + getMessage();
    }
}
