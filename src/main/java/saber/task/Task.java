package saber.task;

/**
 * A class to represent a task
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * A constructor for a task
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * A function to get the status icon of the task depending on the completion status of the task
     * @return X if task is done, and an empty space string if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * A function to get the description of the task
     * @return description of the task in string
     */
    public String getDescription() {
        return description;
    }

    /**
     * A function to get the completion status of the task
     * @return true if the task is done and false otherwise
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * A function to mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A function to return the string representation of a task
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
