package duke.exception;

/**
 * Creates an InvalidDescriptionException that rejects commands with an invalid description.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class InvalidDescriptionException extends Exception {

    /**
     * Constructor for an InvalidDescriptionException.
     *
     * @param message Message for the error.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
