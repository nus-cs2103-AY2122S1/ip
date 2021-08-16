/**
 * The Task class encapsulates a task for the user to complete.
 */
public class Task {
    /** The description of the task. */
    private String description;

    /** A boolean indicating whether the task has been completed. */
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the given task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
