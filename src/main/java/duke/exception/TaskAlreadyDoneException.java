package duke.exception;

/**
 * Thrown to indicate that the task has already been marked as done.
 */
public class TaskAlreadyDoneException extends DukeException {

    /**
     * Constructs a TaskAlreadyDoneException with a detail message.
     */
    public TaskAlreadyDoneException() {
        super("This task has already been done!");
    }
}
