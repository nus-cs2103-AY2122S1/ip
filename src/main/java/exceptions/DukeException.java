package exceptions;

/**
 * This class encapsulates all checked exceptions in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param message The error message to be displayed to user.
     */
    public DukeException(String message) {
        super(message);
    }
}
