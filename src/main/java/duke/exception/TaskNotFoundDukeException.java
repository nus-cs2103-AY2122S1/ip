package duke.exception;

/**
 * TaskNotFoundDukeException class.
 * Used to represent invalid task number given.
 */
public class TaskNotFoundDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! Task %d does not exist.";

    /**
     * TaskNotFoundDukeException constructor.
     */
    public TaskNotFoundDukeException(int taskId) {
        super(String.format(ERROR_MESSAGE, taskId));
    }
}
