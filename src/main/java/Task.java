/**
 * Represents a task.
 *
 * @author felix-ong
 */
public class Task {
    /** Description of the task. */
    protected String description;
    /** Completion status of the task. */
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return "X" if task is done; " " if task is not done.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
