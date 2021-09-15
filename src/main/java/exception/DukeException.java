package exception;

/**
 * Exception class for Duke processes.
 */
public class DukeException extends Exception {
    /**
     * Constructor of the DukeException.
     *
     * @param error The error message.
     */
    public DukeException(String error) {
        super("ERROR - " + error);
    }
}
