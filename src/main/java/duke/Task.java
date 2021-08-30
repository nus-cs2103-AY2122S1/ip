package duke;

public abstract class Task {
    /**
     * Marks the task as done.
     */
    public abstract void markAsDone();
    /**
     * Return Marks the task as done.
     *
     * @return A string representing the task
     */
    public abstract String getTaskString();
}
