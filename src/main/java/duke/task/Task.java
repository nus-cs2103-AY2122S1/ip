package duke.task;

/**
 * A task that can be mark as completed.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * If true, the task is completed. Else, the task is not completed.
     */
    protected boolean isCompleted = false;

    /**
     * A constructor used to initialize the task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * A constructor used to initialize the task through file input.
     *
     * @param description the description of the task.
     * @param isCompleted the state of the task.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a boolean representing if the task matches the string.
     *
     * @param keyword the keyword of the task to check against.
     * @return boolean that represents whether if task matches the keyword.
     */
    public boolean isMatchingTask(String keyword) {
        return description.matches(String.format(".*%s.*", keyword));
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Returns the string representation of task.
     *
     * @return the string representation of task.
     */
    @Override
    public String toString() {
        String displayCompletion = this.isCompleted ? "[X]" : "[]";
        return displayCompletion + ' ' + this.description;
    }

    /**
     * Returns the string representation of task for file input/output.
     *
     * @return the string representation of task.
     */
    public String fileFormat() {
        String displayCompletion = this.isCompleted ? "1" : "0";
        return String.format("%s | %s", displayCompletion, this.description);
    }
}
