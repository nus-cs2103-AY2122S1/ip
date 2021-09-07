package task;

/**
 * Task represents a task.
 *
 * @author Ho Wen Zhong
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status binary.
     *
     * @return Status binary.
     */
    public String getStatusBinary() {
        return (isDone ? "1" : "0");
    }

    /**
     * Sets task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.getDescription();
    }
}
