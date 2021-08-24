package duke.exception;

/** An exception that handles unknown command. */
public class UnknownCommandException extends DukeException {

    /**
     * A constructor for class UnknownCommandException.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
