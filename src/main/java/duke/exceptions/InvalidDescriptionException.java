package duke.exceptions;

/**
 * A Duke exception for invalid descriptions.
 */
public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
