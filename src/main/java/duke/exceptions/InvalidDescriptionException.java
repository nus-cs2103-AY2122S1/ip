package duke.exceptions;

/** Throws error for invalid description, e.g String when int is expected */
public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}