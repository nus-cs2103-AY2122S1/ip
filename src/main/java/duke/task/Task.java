package duke.task;

/**
 * This Task class implements the characteristics of a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task instance that takes in a description.
     * The task is initially not done.
     *
     * @param description The given task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates if the task has been completed.
     *
     * @return A boolean value indicating the completion status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an icon, indicating if the current task has been done.
     *
     * @return A String representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of a Task instance.
     *
     * @return A string representing a Task instance.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
