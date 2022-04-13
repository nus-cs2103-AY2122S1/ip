package duke.task;

/**
 * Represents a Task for the user.
 */
public abstract class Task {
    /**
     * The task description.
     */
    protected String description;
    /**
     * The state of this task being done.
     */
    protected boolean isDone;

    /**
     * Constructs a Task with a description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // task is initialised to be not done
    }

    /**
     * Returns an icon that represents the done state of this task.
     *
     * @return a status icon.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Check if task description contains a string
     */
    public boolean contains(String keyword) {
        // case insensitive
        return toString().toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * The string representation of this task with the status icon and description.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of this task to be written into data storage.
     *
     * @param delimiter The delimiter used by the storage to parse data fields.
     * @return The data string representation of this task.
     */
    public abstract String toDataString(String delimiter);

}
