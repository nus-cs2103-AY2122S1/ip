package exception;

import tasklist.Task;

/**
 * Encapsulates an exception when there is an attempt to add a duplicate task.
 */
public class DuplicateTaskException extends DukeException {
    /**
     * Instantiates an exception when there is an attempt to add a duplicate task.
     * @param task Task that is a duplicate of an existing task.
     */
    public DuplicateTaskException(Task task) {
        super(String.format("You cannot add a duplicate task!\n\nTask '%s' already exists in the list",
                task.toString()));
    }
}
