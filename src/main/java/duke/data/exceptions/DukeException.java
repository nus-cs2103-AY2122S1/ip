package duke.data.exceptions;

/**
 * An abstract class DukeException that is a parent to all exceptions pertaining to Duke.
 */
public abstract class DukeException extends Exception {
    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
