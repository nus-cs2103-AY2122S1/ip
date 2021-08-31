package duke.exception;

/**
 * Represents an exception related to the application with the error message.
 */
public class DukeException extends Exception {
    /**
     * The error message of this exception
     */
    private String message;

    /**
     * Constructs a DukeException with the given error message.
     *
     * @param message The error message of the DukeException.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the error message of the DukeException.
     *
     * @return The error message of the DukeException.
     */
    public String getMessage() {
        return this.message;
    }
}
