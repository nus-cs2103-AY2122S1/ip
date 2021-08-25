package duke.exception;

/**
 * Represents the exception when users enter empty fields in their commands to Duke.
 */
public class EmptyFieldException extends DukeException {

    /**
     * Constructor of the EmptyFieldException class.
     *
     * @param message A string representing the message to display when this exception is caught.
     */
    public EmptyFieldException(String message) {
        super(message);
    }
}
