package duke.exception;

public class IncompleteTaskDescriptionException extends DukeException {
    private String taskName;

    /**
     * Constructs a IncompleteTaskDescriptionException.
     *
     * @param taskName Name of the type of task.
     */
    public IncompleteTaskDescriptionException(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS!!! The description of a %s is either empty or in wrong format.", this.taskName);
    }
}
