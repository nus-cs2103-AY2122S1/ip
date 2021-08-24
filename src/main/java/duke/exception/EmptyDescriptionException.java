package duke.exception;

/** An exception that handles command with empty description. */
public class EmptyDescriptionException extends DukeException {

    /**
     * A constructor for class EmptyDescriptionException.
     *
     * @param messgae The message to be displayed when this exception is caught.
     */
    public EmptyDescriptionException(String messgae) {
        super(messgae);
    }
}
