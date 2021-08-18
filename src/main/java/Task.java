/**
 * Represents a task to be done. A task includes a description and
 * whether it had been done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the current Status Icon of the task.
     *
     * @return String "X" if task is done and an empty string if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X":" ");
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the isDone boolean of the task to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of task.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
