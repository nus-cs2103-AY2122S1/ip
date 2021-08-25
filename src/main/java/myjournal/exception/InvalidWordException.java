package myjournal.exception;

/**
 * A class to create invalid task number exceptions.
 *
 * @author Felissa Faustine
 */
public class InvalidWordException extends MyJournalException {

    /**
     * The constructor for the InvalidTaskNumberExceptions.
     *
     * @param message The error message.
     */
    public InvalidWordException(String message) {
        super(message);
    }

    /**
     * Gets the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "InvalidWordException: " + getMessage();
    }
}