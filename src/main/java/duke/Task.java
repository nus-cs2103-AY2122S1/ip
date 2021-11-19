package duke;

/**
 * duke.Task represents the task in the Duke
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Shows X if the task is marked done.
     *
     * @return X if task is completed else whitespace
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the task to Completed
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Returns the description for the task.
     *
     * @return the description for the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is completed or not.
     *
     * @return whether the task is completed or not
     */
    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}