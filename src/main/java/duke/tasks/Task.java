package duke.tasks;

/**
 * Class representing the Task type.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description of the Task.
     * @param isDone      if the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task depending on if it is done or not.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
