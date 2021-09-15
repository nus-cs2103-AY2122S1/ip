package exception;

/**
 * Exception class for valid commands with invalid index.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor of the InvalidIndexException.
     *
     * @param error The error message.
     */
    public InvalidIndexException(String error) {
        super("[Invalid Index] " + error);
    }
}
