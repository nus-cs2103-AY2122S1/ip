package duke.exception;

/**
 * Signals that a task is not inside the task list.
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
