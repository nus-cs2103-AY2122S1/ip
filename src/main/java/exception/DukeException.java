package exception;

/**
 * The DukeException is the superclass of all exceptions to be thrown from the app.
 */
public class DukeException extends Exception {

    private String error;

    /**
     * Constructs a new DukeException.
     *
     * @param message error message to be shown.
     */
    public DukeException(String message) {
        super(message);
        this.error = message;
    }

    /**
     * Returns a string description of exception.
     *
     * @return a string description of exception.
     */
    @Override
    public String toString() {
        return this.error;
    }
}
