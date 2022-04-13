package duke.exception;

/**
 * NoMatchingTaskDukeException class.
 * Used to represent a null search for task.
 */
public class NoMatchingTaskDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "No matching tasks found.";

    /**
     * NoMatchingTaskDukeException constructor.
     */
    public NoMatchingTaskDukeException() {
        super(ERROR_MESSAGE);
    }
}
