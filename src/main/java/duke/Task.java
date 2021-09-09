package duke;

/**
 * A class representing a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A constructor for a task.
     *
     * @param isDone The status of the task.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task status as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the backup format of the task.
     *
     * @return The backup format of the task.
     */
    public String toBackupFormat() {
        return String.format("%s | %d", description, isDone ? 1 : 0);
    }

    /**
     * Returns true if the tasks clashed.
     *
     * @return false.
     */
    public boolean clash(Task t) {
        return false;
    }
}
