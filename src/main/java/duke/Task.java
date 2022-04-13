package duke;

/**
 * Parent class that represents all tasks.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the task class.
     * It is assumed that a created task is not done yet.
     * @param description the description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the task class with status.
     * @param description the description for the Task.
     * @param status the status of the task.
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    /**
     * Returns the status icon of the task.
     * @return the status icon of the task based on whether the task has been done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the selected task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the Task in the string form.
     * @return the Task in string form.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;

    }

}
