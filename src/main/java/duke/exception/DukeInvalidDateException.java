package duke.exception;

/**
 * Exception for invalid date input in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeInvalidDateException extends DukeException {
    /**
     * Constructor for DukeInvalidDateException.
     */
    public DukeInvalidDateException() {
        super("Invalid Date @_@\nDate formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd");
    }
}
