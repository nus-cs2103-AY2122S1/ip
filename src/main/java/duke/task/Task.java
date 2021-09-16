package duke.task;

/**
 * An abstract class that represents tasks
 */
public abstract class Task {
    private TaskType type;
    private String description;
    private boolean isDone;

    /**
     * Initializes an instance of Task class with task type and description.
     * @param type Type of task
     * @param description Task description
     */
    public Task(TaskType type, String description) {
        // By default, assume that the task has not been completed.
        this(type, description, false);
    }

    /**
     * Initializes an instance of Task class with task type, description and
     * a flag to indicate if the task has been completed.
     * @param type Type of task
     * @param description Task description
     * @param isDone Flag to indicate if the task has been completed
     */
    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the type of task.
     * @return Type of task
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Gets the task description.
     * @return String containign the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a flag to indicate if the task has been completed.
     * @return The boolean flag
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets status icon for the task.
     * @return The string representation of the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[ ]"); // mark done task with ✔
    }

    /**
     * Sets the isDone flag to true to indicate tat the task has been completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Finds if the task is on a specified date.
     * The function will be overridden in derived classes.
     * @param dateStr The date by which the task is to be completed
     * @return False because, by default, a task is not on a specified date
     */
    public boolean isOnDate(String dateStr) {
        return false;
    }

    /**
     * Gets a description of the task.
     * @return A string containign description of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
