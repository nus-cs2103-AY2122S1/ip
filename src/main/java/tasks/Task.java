package tasks;

/**
 * Represents a task.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description.
     * @param description The description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of this task.
     * @return A string representing the status of this task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns this task's status and description.
     * @return A string representing the status of this task and its description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
