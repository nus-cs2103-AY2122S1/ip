package duke.task;

/**
 * Represents a Task which contains a String description and a boolean status.
 *
 * @author Sherman Ng Wei Sheng
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor to initialize a new Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to initialize a new Task with a boolean argument.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the particular task.
     *
     * @return The status icon depending on whether the task is marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
        assert isDone : "task should be marked done";
    }

    /**
     * Returns the string representation of the task in a desired format.
     *
     * @return The String representation of the task, prefixed with a status icon.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
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
     * Returns the boolean value whether the task is done.
     *
     * @return True if the task is marked done and false if otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the encoded string for storage.
     *
     * @return The string that is suitable for saving the data.
     */
    public abstract String encodeTaskForStorage();
}
