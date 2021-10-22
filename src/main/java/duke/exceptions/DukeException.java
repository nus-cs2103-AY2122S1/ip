package duke.exceptions;

/**
 * Encapsulates exceptions for Duke bot
 */
public class DukeException extends RuntimeException {
    private final String message;

    /**
     * Public constructor for a DukeException object.
     * @param message
     */
    public DukeException(String message) {
        super(message);
        assert message != null : "DukeException message should not be null.";
        this.message = message;
    }

    /**
     * Returns a string containing the error message with "OOPS!" appended at the front
     *
     * @return A string with the error message.
     */
    @Override
    public String toString() {
        return "OOPS! " + this.message;
    }
}
