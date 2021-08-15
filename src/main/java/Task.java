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
}
