package duke.exceptions;

/**
 * A Duke Exception for missing descriptions after commands.
 */
public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String message) {
        super(message);
    }
}
