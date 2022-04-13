package duke.data.task;

/**
 * This class abstracts a task.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of a task.
     * @return "X" if isDone is true and " " if isDone is false.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the data representation of a Task object.
     *
     * @return The formatted String.
     */
    public String toData() {
        return getStatusIcon() + "|" + description;
    }

    /**
     * Returns the String representation of a Task object.
     *
     * @return THe String representation of a Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
