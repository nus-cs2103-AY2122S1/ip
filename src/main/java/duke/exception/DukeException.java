package duke.exception;

/**
 * Represents problems encountered specifically with Duke.
 */
public class DukeException extends Exception {
    private final String message;

    /**
     * Class constructor.
     *
     * @param message Exception message to be displayed.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message
     *
     * @return Exception message.
     */
    public String getMessage() {
        return this.message;
    }
}
