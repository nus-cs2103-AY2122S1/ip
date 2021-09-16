package duke.exception;

/**
 * Exception for missing date or time in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeMissingDateTimeException extends DukeException {
    /**
     * Constructor for DukeMissingDateTimeException.
     */
    public DukeMissingDateTimeException() {
        super("Error: Date/Time is missing!");
    }
}
