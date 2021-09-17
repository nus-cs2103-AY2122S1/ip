package myjournal.exception;

/**
 * Creates empty description exceptions.
 *
 * @author Felissa Faustine.
 */
public class EmptyDescriptionException extends MyJournalException {

    /**
     * Constructs the EmptyDescriptionException objects.
     *
     * @param message The error message.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }

    /**
     * Gets the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "EmptyDescriptionException: " + getMessage();
    }
}
