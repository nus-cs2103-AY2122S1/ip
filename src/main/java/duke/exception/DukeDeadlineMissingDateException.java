package duke.exception;

/**
 * This class encapsulates exception due to missing date descriptions.
 *
 * @author Teo Sin Yee
 */
public class DukeDeadlineMissingDateException extends DukeException {
    public DukeDeadlineMissingDateException() {
        super("Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.");
    }
}