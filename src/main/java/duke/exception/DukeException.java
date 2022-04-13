package duke.exception;

/**
 * Represents all Exceptions related to Duke.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class DukeException extends Exception {
    /**
     * Constructor of the DukeException class.
     *
     * @param message A string representing the message to display when the exception is caught.
     */
    public DukeException(String message) {
        super(message);
    }
}
