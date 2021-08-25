package duke.exception;

/**
 * Represents the exception when users enter invalid fields in their commands to Duke.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor of the EmptyFieldException class.
     *
     * @param message A string representing the message to display when this exception is caught.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
