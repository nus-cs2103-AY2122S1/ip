package duke.task;

/**
 * Represents a Task object.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public abstract class Task {

    /** String containing the description of the task. */
    protected String description;
    /** Boolean that states whether the task is completed. */
    protected boolean isDone;

    /**
     * Constructor for creating a task.
     *
     * @param description The deadline's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether the task is completed.
     * "X" to represented completed and " " to represent incomplete.
     *
      * @return String of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return String of the task description.
     */
    public String getDescription() {
        return description;
    }

    public abstract String formatSave();

    /**
     * Returns String representation of Task.
     *
     * @return String representation of the task.
     */
    public String toString() {
        assert description != null;
        return "[" + getStatusIcon() + "] " + description;
    }
}
