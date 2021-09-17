package duke.task;

public abstract class Task {
    /**
     * Marks the task as done.
     */
    public abstract void markAsDone();
    /**
     * Marks the task as done.
     *
     * @return A string representing the task
     */
    public abstract String getTaskString();
}
