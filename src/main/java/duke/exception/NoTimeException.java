package duke.exception;

/** Exception to be thrown when a time is expected in the creation of a task but is not present */
public class NoTimeException extends DukeException {
    /**
     * Constructor for a NoTimeException
     */
    public NoTimeException() {
        super("Please specify a time for the task in YYYY-MM-DD (e.g. 2021-09-14)");
    }
}
