package duke.exceptions;

/**
 * A Duke exception for missing time commands.
 *
 * E.g. /at or /by commands.
 */
public class MissingTimeCommandException extends DukeException {
    public MissingTimeCommandException(String message) {
        super(message);
    }
}
