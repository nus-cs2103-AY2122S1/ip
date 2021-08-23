package duke.exception;

/**
 * Thrown to indicate that the task description has not been provided by the
 * user when attempting to add a task to the task list.
 */
public class EmptyTaskDescriptionException extends DukeException {

    /**
     * Constructs an EmptyTaskDescriptionException with an argument
     * indicating the specified task type.
     *
     * @param taskType The type of the task to be added.
     */
    public EmptyTaskDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty!");
    }
}
