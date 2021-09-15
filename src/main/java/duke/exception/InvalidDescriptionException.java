package duke.exception;

public class InvalidDescriptionException extends DukeException {

    /**
     * Constructs an InvalidDescriptionException instance that handles command with invalid Description.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
