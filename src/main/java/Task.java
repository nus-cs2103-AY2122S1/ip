/**
 * This class represents a user's task.
 */
public class Task {
    private String taskName;
    private boolean complete;

    /**
     * Constructor for a task. When tasks are initialised, they are marked as not complete.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.complete = false;
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Toggles the completion status of the task.
     */
    public void toggleComplete() {
        this.complete = !this.complete;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Returns X if task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (complete ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     * This displays the completion status of the task along with its name.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), taskName);
    }
}
