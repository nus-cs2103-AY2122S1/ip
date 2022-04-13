package duke.exceptions;

/**
 * The condition where the user creates a task that already exists.
 * Specifically, a task with the same task type, name and time (if applicable)
 * is already in the task list.
 */
public class DuplicateTaskException extends DukeException {
    /**
     * Creates a duplicate task exception instance with a standard detail message.
     */
    public DuplicateTaskException() {
        super("You have already created this task!");
    }
}
