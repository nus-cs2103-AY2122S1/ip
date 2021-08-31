package duke.task;

/**
 * Base class for all task classes. Contains a description of the task and a boolean value indicating
 * if the task is completed.
 */
public class Task {
    /**
     * The description for the task
     */
    protected String description;
    /**
     * The completion status of the task
     */
    protected boolean isDone;

    /**
     * Creates a task class with the given description and the completion status.
     *
     * @param description Description of the task.
     * @param isDone      Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status icon of the task depending on the completion status. The status icon is used
     * when displaying the task in the UI.
     *
     * @return the status icon for this task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the string representation of the task meant to be written to the data file.
     *
     * @return the string representation of the task to be written to the data file.
     */
    public String toDataString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the string representation of the task meant to be displayed in the UI.
     *
     * @return the string representation of the task to be displayed in the UI.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
