package duke.task;

/**
 * A task added by the user containing a description.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The status of the task. */
    protected boolean isDone;

    /**
     * Public constructor to create a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of its status.
     *
     * @return String representation of its status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the formatted String representation
     * of the task to write into the save file.
     *
     * @return Formatted String representation of the task.
     */
    public abstract String formatSave();
}
