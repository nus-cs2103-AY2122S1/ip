package saber.task;

/**
 * Represents a task
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs for a task
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the task depending on the completion status of the task
     *
     * @return X if task is done, and an empty space string if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Gets the description of the task
     *
     * @return description of the task in string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the completion status of the task
     *
     * @return true if the task is done and false otherwise
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of a task
     *
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
