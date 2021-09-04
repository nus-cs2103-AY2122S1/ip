package duke.exception;

/**
 * Exception for invalid time input in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeInvalidTimeException extends DukeException {
    /**
     * Constructor for DukeInvalidTimeException.
     */
    public DukeInvalidTimeException() {
        super("Invalid Time @_@\nTime format: HHmm HH:mm");
    }
}
