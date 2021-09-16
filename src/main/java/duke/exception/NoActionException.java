package duke.exception;

/**
 * Signals that an input did not provide a action.
 */
public class NoActionException extends DukeException {
    public NoActionException(String errorMessage) {
        super(errorMessage);
    }
}
