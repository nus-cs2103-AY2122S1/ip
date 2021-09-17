package duke.task;

/**
 * Represents a Task, which has a name and can be marked as done.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

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
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
         return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

}
