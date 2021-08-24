package duke.exception;

/**
 * A subclass of DukeException. Thrown when users do not give a valid deadline to the task.
 *
 */
public class InvalidDeadlineException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public InvalidDeadlineException() {
        super("Please enter deadline in the format yyyy-mm-dd");
    }
}
