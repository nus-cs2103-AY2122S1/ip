package duke.exception;

/**
 * Creates an InvalidInputException that rejects an invalid input;
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor for an InvalidInputException.
     *
     * @param message Message for the error.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
