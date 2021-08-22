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
    protected Task(String description) {
        this.description = description;
    }

    /**
     * A constructor used to initialize the task through file input.
     *
     * @param description the description of the task.
     * @param isCompleted the state of the task.
     */
    protected Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as done.
     */
    protected void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Return the string representation of task.
     *
     * @return the string representation of task.
     */
    @Override
    public String toString() {
        String displayCompletion = this.isCompleted ? "[X]" : "[]";
        return displayCompletion + ' ' + this.description;
    }

    /**
     * Return the string representation of task for file input/output.
     *
     * @return the string representation of task.
     */
    public String fileFormat() {
        String displayCompletion = this.isCompleted ? "1" : "0";
        return String.format("%s | %s", displayCompletion, this.description);
    }
}
