package duke;

/**
 * Represents a task.
 */
public abstract class Task {
    private String task;
    private boolean isCompleted;
    private String taskType;

    /**
     * Class constructor specifying the task description.
     * @param task Task description.
     */
    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
        this.taskType = " ";
    }

    /**
     * Class constructor specifying the task description and the task type.
     * @param task Task description.
     * @param taskType Task type.
     */
    public Task(String task, String taskType) {
        this.task = task;
        this.isCompleted = false;
        this.taskType = taskType;
    }

    /**
     * Class constructor specifying the task description, if the task was completed and the task type.
     * @param task Task description.
     * @param isCompleted Whether the task is completed.
     * @param taskType Task type.
     */
    public Task(String task, boolean isCompleted, String taskType) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.taskType = taskType;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns a marker to represent if the task has been completed.
     * @return String marker denoting if task has been completed.
     */
    public String getCompletedMarker() {
        return (this.isCompleted ? "X" : " ");
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean containsKeyword(String keyword) {
        return task.contains(keyword);
    }

    public boolean setCompleted() {
        this.isCompleted = true;
        return this.isCompleted;
    }

    public String getTask() {
        return this.task;
    }

    /**
     * Parses the task to the appropriate format for local storage.
     * @return String representation of task for storage.
     */
    public abstract String parseForStorage();
}
