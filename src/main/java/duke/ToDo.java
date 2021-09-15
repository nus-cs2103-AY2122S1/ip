package duke;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    /**
     * Class constructor specifying the task description.
     * @param task Task description.
     */
    public ToDo(String task) {
        super(task, TASK_TYPE);
    }

    /**
     * Class constructor specifying the task description and if the task has been completed.
     * @param task Task description
     * @param completed Whether the task has been completed.
     */
    public ToDo(String task, boolean completed) {
        super(task, completed, TASK_TYPE);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskType(), this.getCompletedMarker(), this.getTask());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %s", this.getTaskType(), this.getIsCompleted() ? 1 : 0, this.getTask());
    }
}
