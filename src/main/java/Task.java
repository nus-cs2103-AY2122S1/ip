/**
 * A wrapper for a task used by DukeList.
 *
 * @author Wong Yun Rui Chris
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A public constructor to initialise a Task.
     *
     * @param description The String description/name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the corresponding status icon for this task.
     *
     * @return The String representation of the status for this task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the status of this task as done.
     *
     * @return The new String representation of this Task after the status is marked as done
     */
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }
}