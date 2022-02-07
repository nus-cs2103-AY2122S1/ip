package duke.task;

/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Show whether a task is done.
     *
     * @return String representation of the status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Mark a task as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return ("Yayyyy done ~~\n" + toString());
    }

    /**
     * Check whether a task is done.
     *
     * @return True if it is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * String representation of a task.
     *
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        return ("|" + getStatusIcon() + "| " + this.description);
    }

    /**
     * String format of a task that will be stored to the file.
     *
     * @return String to be stored.
     */
    public String toStoredString() {
        return "";
    }

}
