package duke.exceptions;

/**
 * Exception to indicate when the user forgets to enter task number.
 * e.g. done
 */
public class MissingTaskNumberException extends DukeException {
    public MissingTaskNumberException(String error) {
        super(error);
    }
}
