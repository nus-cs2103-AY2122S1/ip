package duke.exception;

/**
 * Exception for I/O errors in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeIoException extends DukeException {
    /**
     * Constructor for DukeIoException.
     */
    public DukeIoException() {
        super("I/O error from file @_@");
    }
}
