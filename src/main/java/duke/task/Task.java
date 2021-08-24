package duke.task;

/**
 * Tracks a task with a description and whether it has been completed yet.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets this task to be done.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the duke.Task's String.
     *
     * @return String form of duke.Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public int getTaskType() {
        return -1;
    }

    public String toSavedFormat() {
        return "";
    }

    public boolean isDone() {
        return this.isDone;
    }
}
