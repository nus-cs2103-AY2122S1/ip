/**
 * This class represents a task.
 */

public class Task {
    /** Description of the task. */
    protected String description;
    /** State of the task. */
    protected boolean isDone;

    /**
     * Constructs a task instance using the given description.
     *
     * @param description the given description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X when the task is done, space otherwise.
     *
     * @return X when the task is done, space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
