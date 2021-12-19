package duke.task;

/**
 * The task in the list.
 */
public abstract class Task {
    /**
     * description of the task.
     */
    protected String description;

    /**
     * The task status. True if it is done, else false.
     */
    protected boolean isDone;

    /**
     * Creates a new Task object (Constructor).
     *
     * @param description The new task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the task status icon.
     *
     * @return "X" if the test is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public String doneTask() {
        this.isDone = true;
        return "Nice! I've marked this task as done: \n" + this;
    }

    /**
     * Makes deep copy of itself
     *
     * @return Task deep copy of itself
     */
    public abstract Task duplicate();

    /**
     * Generates the task string representation.
     *
     * @return The description of the task.
     */
    public String toString() {
        return "| " + getStatusIcon() + " | " + description;
    }
}
