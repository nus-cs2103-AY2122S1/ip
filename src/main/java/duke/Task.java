package duke;

/**
 * Represents a task.
 */
public abstract class Task {
    private String task;
    private boolean completed;
    private String taskType;

    /**
     * Class constructor specifying the task description.
     * @param task Task description.
     */
    public Task(String task) {
        this.task = task;
        this.completed = false;
        this.taskType = " ";
    }

    /**
     * Class constructor specifying the task description and the task type.
     * @param task Task description.
     * @param taskType Task type.
     */
    public Task(String task, String taskType) {
        this.task = task;
        this.completed = false;
        this.taskType = taskType;
    }

    /**
     * Class constructor specifying the task description, if the task was completed and the task type.
     * @param task Task description.
     * @param completed Whether the task is completed.
     * @param taskType Task type.
     */
    public Task(String task, boolean completed, String taskType) {
        this.task = task;
        this.completed = completed;
        this.taskType = taskType;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    /**
     * Returns a marker to represent if the task has been completed.
     * @return String marker denoting if task has been completed.
     */
    public String getCompletedMarker() {
        return (this.completed? "X" : " ");
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean setCompleted() {
        this.completed = true;
        return this.completed;
    }

    public String getTask() {
        return this.task;
    }

    /**
     * Parses the task to the appropriate formate for local storage.
     * @return String representation of task for storage.
     */
    public abstract String parseForStorage();
}
