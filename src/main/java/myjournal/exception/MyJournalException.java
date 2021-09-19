package myjournal.exception;

/**
 * Creates MyJournal Exceptions.
 *
 * @author Felissa Faustine.
 */
public class MyJournalException extends RuntimeException {
    /**
     * Constructs MyJournalException objects.
     *
     * @param message The error message.
     */
    public MyJournalException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "MyJournalException: " + getMessage();
    }
}
