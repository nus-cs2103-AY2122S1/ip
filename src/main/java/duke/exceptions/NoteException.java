package duke.exceptions;

/**
 * Exception that is thrown when a Note class cannot be created due to empty description provided by user.
 */
public class NoteException extends DukeException {
    /**
     * Creates an object of the NoteException class.
     */
    public NoteException() {
        super();
    }

    /**
     * Gets the message to be printed in response to the error.
     *
     * @return String message in response to the error thrown.
     */
    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a note cannot be empty.";
    }
}
