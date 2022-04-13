package duke.exception;

/** Exception to be thrown when a task cannot be found */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructor for a TaskNotFoundException
     */
    public TaskNotFoundException() {
        super("Task does not exist!");
    }
}
