package tasks;

/**
 * This is a Task Class, which encapsulates task name and
 * task status (done / not done).
 */
public class Task {
    protected final String taskName;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param taskName The description of the task.
     */
    public Task(String taskName) {
        assert taskName != null;
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * @return The boolean status of the task.
     */
    public boolean getStatus() {
        // Mark done task with X
        return isDone;
    }

    /**
     * Returns "true" if task is done, "false otherwise.
     * @return The string "true" if task is done, "false otherwise.
     */
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Returns the task name.
     * @return The task name.
     */
    public String getDescription() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "]  " + this.taskName;
    }
}
