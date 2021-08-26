package duke;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task object.
     * @param description String representing description of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructor for a Task object.
     * @param description String representing description of the task.
     * @param isDone Boolean flag representing whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Toggle the <code>isDone</code> flag of the task.
     * @param isDone Desired boolean value.
     */
    public void toggle(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter for the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the done status of the task.
     * @return Boolean representing whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Getter for the status icon of the task.
     * @return String representing the status icon of the task.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }
}
