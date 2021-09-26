package duke.exception;

/**
 * Signals that an exception in the Duke program has occurred.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the given message.
     *
     * @param message Message of this DukeException.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a description of this DukeException.
     *
     * @return a description of this DukeException.
     */
    @Override
    public String toString() {
        return "OOPs!!! " + super.getMessage();
    }
}
