package jarvis.exception;

/**
 * Encapsulates an exception when the task list is empty.
 */
public class TaskListEmptyException extends JarvisException {
    /**
     * Constructor for TaskListEmptyException.
     */
    public TaskListEmptyException() {
        super("Please add some tasks first!");
    }
}
