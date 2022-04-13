package duke;

/**
 * This class represents each task registered by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task activity.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Represents the task status.
     *
     * @return Shows X for completed, and blank for non-completed task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter to mark a task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * toString method to represent Task into a String.
     *
     * @return Shows both the status and the description of the activity.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
