package janet;

import java.io.Serializable;

/**
 * Represents a task, which has a description and may be done or not done.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor which takes in the description of the task. The task
     * is assumed to not be done at the time of creation.
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns an "X" if the task is done, and a whitespace otherwise.
     *
     * @return "X" or whitespace depending on whether task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task to be done or not done.
     *
     * @param bool Whether the task should be marked as done
     */
    public void setDone(boolean bool) {
        isDone = bool;
    }

    /**
     * Returns the string representation of the task, including whether it
     * has been completed.
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
