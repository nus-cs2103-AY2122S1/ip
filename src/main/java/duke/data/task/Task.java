package duke.data.task;

/**
 * Class that represents a task.
 *
 * @author Won Ye Ji
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the done status of the task.
     *
     * @return string representation of the done status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public abstract String toSave();
}
