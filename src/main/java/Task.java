/**
 * A Task class encapsulates the data related to a task
 * ie task description and status.
 *
 * @author Lethicia
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description the title or description for the task
     */
    public Task(String description) {
        this.description = description;
        /** task marked as incomplete by default. */
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return X to mark task as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Represents the task description and status
     *
     * @return details of the task in a given format
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
