package duke.exception;

/**
 * This class encapsulates exception due to missing date descriptions.
 *
 * @author Teo Sin Yee
 */
public class DukeDeadlineMissingDateException extends DukeException {
    /**
     * Constructor for DukeDeadlineMissingDateException
     */
    public DukeDeadlineMissingDateException() {
        super("Error: Invalid use of 'deadline' command!! @_@\n\t"
                + "To add a new deadline, use 'deadline <task> /by <due-date>'.");
    }
}