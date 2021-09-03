package duke.task;

/**
 * Encapsulates the abstract Task class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for new task objects as input by user.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for new task objects as read from saved file.
     *
     * @param description Description of the deadline task.
     * @param isDone isDone Indicates if task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String getTypeIndicator();

    public abstract String toFileRecord();

    /**
     * Gets the completion status of the task.
     *
     * @return The String representation of status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns whether the description of the task contains a given keyword.
     *
     * @return Whether the keyword is found.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns a String representation of the task.
     *
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        return getTypeIndicator() + " [" + getStatusIcon() + "] " + description;
    }
}
