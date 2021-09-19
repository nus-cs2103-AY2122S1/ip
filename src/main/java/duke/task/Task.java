package duke.task;

/**
 * Represents a Task, which has a name and can be marked as done.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param taskName The name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the status icon of the task, X means done, empty means undone.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

}
