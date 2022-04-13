package duke.exception;

/**
 * Creates an InvalidTimeException that rejects commands with an invalid time.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class InvalidTimeException extends Exception {
    /**
     * Constructor for an InvalidTimeException.
     *
     * @param message Message for the error.
     */
    public InvalidTimeException(String message) {
        super(message);
    }
}
