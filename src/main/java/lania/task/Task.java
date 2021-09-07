package lania.task;

/**
 * Represents the task that a user has.
 */
public abstract class Task {

    /** String containing the name of task */
    protected String description;
    /** Whether the task is done */
    protected boolean isDone;

    /**
     * Constructor for Task which consists of a description and whether the task is done.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets an icon representation depending on whether task is done.
     *
     * @return An indicator for whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Completes a task and set its isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Completes a task and set its isDone to true.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * String representation of a task object
     * to be stored in the hard disk.
     *
     * @return A string representation of the task object.
     */
    public abstract String getStringFormat();

    /**
     * String representation of the task object
     * to be displayed to the user.
     *
     * @return Another string representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
