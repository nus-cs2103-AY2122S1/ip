package duke.exception;

/**
 * The DukeException class encapsulates exceptions that
 * are thrown while Duke is operating.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a Duke exception with a specified message.
     *
     * @param message The message containing information on the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the Duke exception.
     *
     * @return String representing the Duke exception.
     */
    @Override
    public String toString() {
        return "Error detected by Duke:\n" + getMessage();
    }
}
