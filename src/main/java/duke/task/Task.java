package duke.task;

public abstract class Task {
    /**
     * Sets a reference id for the task
     */
    public abstract void setRefId(int n);

    /**
     * Gets the reference id for the task
     */
    public abstract int getRefId();

    /**
     * Marks the task as done.
     */
    public abstract void markAsDone();


    /**
     * Returns a string representing the task.
     *
     * @return A string representing the task
     */
    public abstract String getTaskString();
}
