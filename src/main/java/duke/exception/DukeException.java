package duke.exception;

/**
 * Represents an exception thrown when invalid commands are received.
 */
public class DukeException extends Exception {
    /**
     * Constructor of the class `DukeException`.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super("____________________________________________________________\n" +
                message +
                "\n____________________________________________________________\n");
    }

    /**
     * Returns the error message of the DukeException.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
