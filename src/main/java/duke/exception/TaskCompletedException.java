package duke.exception;

/**
 * Signals that the task selected has already been completed.
 */
public class TaskCompletedException extends DukeException {
    public TaskCompletedException(String errorMessage) {
        super(errorMessage);
    }
}
