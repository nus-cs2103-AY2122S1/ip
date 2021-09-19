package duke;

/**
 * Represents a task.
 */
public abstract class Task {
    private String task;
    private boolean isCompleted;
    private String taskType;
    private int priority;

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
    public Task(String task, String taskType, int priority) {
        this.task = task;
        this.isCompleted = false;
        this.taskType = taskType;
        this.priority = priority;
    }

    /**
     * Class constructor specifying the task description, if the task was completed and the task type.
     * @param task Task description.
     * @param isCompleted Whether the task is completed.
     * @param taskType Task type.
     */
    public Task(String task, boolean isCompleted, String taskType, int priority) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.taskType = taskType;
        this.priority = priority;
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

    public String getPriorityMarker() {
        switch (priority) {
        case 1:
            return "!";
        case 2:
            return "*";
        case 3:
            return " ";
        default:
            return "?";
        }
    }

    public String getTaskType() {
        return this.taskType;
    }

    public int getPriority() {
        return this.priority;
    }

    public int setPriority(int priority) {
        this.priority = priority;
        return priority;
    }

    /**
     * Checks if task description contains keyword.
     * @param keyword Keyward to be found.
     * @return true if task description contains keyword, else false.
     */
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
