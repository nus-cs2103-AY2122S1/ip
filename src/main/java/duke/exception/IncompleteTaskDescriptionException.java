package duke.exception;

/**
 * Exception due to user inputting a command to add task but the task description is incomplete.
 */
public class IncompleteTaskDescriptionException extends DukeException {
    private String taskName;

    /**2
     * Constructs a IncompleteTaskDescriptionException.
     *
     * @param taskName Name of the type of task.
     */
    public IncompleteTaskDescriptionException(String taskName) {
        super();
        this.taskName = taskName;
    }

    /**
     * Returns a message to inform user about the task that is incomplete.
     *
     * @return A message to inform user about the task that is incomplete.
     */
    @Override
    public String getMessage() {
        return String.format("☹ OOPS!!! The description of a %s is either empty or in wrong format.", this.taskName);
    }
}
