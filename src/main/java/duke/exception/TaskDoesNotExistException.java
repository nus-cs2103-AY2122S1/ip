package duke.exception;

/**
 * A subclass of DukeException, thrown when search cannot be found.
 *
 */
public class TaskDoesNotExistException extends DukeException {
    /**
     * Constructor of the exception.
     */
    public TaskDoesNotExistException() {
        super("Sorry. No such task is found :( Try another key word?");
    }
}
