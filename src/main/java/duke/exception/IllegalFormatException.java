package duke.exception;

/**
 * Thrown when user's input does not follow the given format.
 *
 * @author Zhang Shi Chen
 */
public class IllegalFormatException extends DukeException {
    public IllegalFormatException(String format) {
        super("Please follow this format:\n  " + format);
    }
}
