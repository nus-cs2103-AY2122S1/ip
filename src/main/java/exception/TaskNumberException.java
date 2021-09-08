package exception;

/**
 * The TaskNumberException is thrown when a task number referenced is not in the task list.
 */
public class TaskNumberException extends DukeException {

    /**
     * Constructs a new TaskNumberException.
     */
    public TaskNumberException() {
        super("The number is not in the list!");
    }
}
