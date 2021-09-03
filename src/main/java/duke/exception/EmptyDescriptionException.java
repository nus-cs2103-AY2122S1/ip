package duke.exception;

/**
 * An exception that handles command with empty description.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs an EmptyDescriptionException instance that handles command with empty description.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
