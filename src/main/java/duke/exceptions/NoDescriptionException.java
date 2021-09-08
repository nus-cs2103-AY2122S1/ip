package duke.exceptions;

/** Throws error for missing description after command */
public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String message) {
        super(message);
    }
}