package botto.task;

/**
 * Format for the Botto bot's task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, else " ".
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
     * Returns the task description.
     *
     * @return the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
