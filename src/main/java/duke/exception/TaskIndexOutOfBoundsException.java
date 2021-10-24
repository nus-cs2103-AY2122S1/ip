package duke.exception;

/**
 * Thrown to indicate that the task list is accessed with an illegal index.
 */
public class TaskIndexOutOfBoundsException extends DukeException {

    /**
     * Constructs a TaskIndexOutOfBoundsException with a detail message.
     */
    public TaskIndexOutOfBoundsException() {
        super("Invalid task index specified!");
    }
}
