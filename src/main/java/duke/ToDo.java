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
    public ToDo(String task, int priority) {
        super(task, TASK_TYPE, priority);
    }

    /**
     * Class constructor specifying the task description and if the task has been completed.
     * @param task Task description
     * @param completed Whether the task has been completed.
     */
    public ToDo(String task, boolean completed, int priority) {
        super(task, completed, TASK_TYPE, priority);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s] %s", this.getTaskType(), this.getCompletedMarker(),
                this.getPriorityMarker(), this.getTask());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %d | %s", this.getTaskType(), this.getIsCompleted() ? 1 : 0,
                this.getPriority(), this.getTask());
    }
}
