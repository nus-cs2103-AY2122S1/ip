package myjournal.exception;

/**
 * Creates the invalid type exceptions.
 *
 * @author Felissa Faustine.
 */
public class InvalidTypeException extends MyJournalException {

    /**
     * Constructs the InvalidTypeException objects.
     *
     * @param message The error message.
     */
    public InvalidTypeException(String message) {
        super(message);
    }

    /**
     * Gets the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "InvalidTypeException: " + getMessage();
    }
}
