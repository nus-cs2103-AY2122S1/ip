package duke.tasks;

/**
 * Represents a Task which has a String description of what the task is and
 * a boolean to indicate if the task is completed.
 *
 * @author ruiquan
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task given the description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task given both the description and completion status.
     *
     * @param description The task description.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a String icon to indicate if the task is completed.
     *
     * @return "X" if the task is completed and an empty string if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the Task as completed.
     *
     * @return The completed Task.
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Formats the Task into a String that will be saved into a text file for storage.
     *
     * @return A String representation of the task.
     */
    public abstract String format();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
