package duke.task;

/**
 * Represents a ToDo of the user which only has a description and completion status.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo class with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status for the task.
     */
    public ToDo(String description, boolean isDone) {
        super(TaskType.TODO, description, null, isDone);
    }
}
