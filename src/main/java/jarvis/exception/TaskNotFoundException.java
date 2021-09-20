package jarvis.exception;

/**
 * Encapsulates an exception when the task is not found in the task list.
 */
public class TaskNotFoundException extends JarvisException {
    /**
     * Constructor for TaskNotFoundException.
     */
    public TaskNotFoundException() {
        super("Please enter a valid task number!");
    }
}
