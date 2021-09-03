package duke.exception;

/**
 * An exception that handles command with incomplete description.
 */
public class IncompleteDescriptionException extends DukeException {

    /**
     * Constructs an IncompleteDescriptionException instance that handles command with incomplete description.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public IncompleteDescriptionException(String message) {
        super(message);
    }
}
