package duke.task;

/**
 * Abstract parent class for all possible tasks stored in Duke.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task class.
     *
     * @param description description of a task in String.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the status icon of a task. "X" for done, and
     * space for not done.
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Function to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Abstract method that returns the task
     * in a string for print.
     *
     * @return task represented in a string for print.
     */
    public abstract String toString();

    /**
     * Abstract method that returns the task
     * in a string for sending to the data file to save.
     *
     * @return task represented in a string for saving in the data file.
     */
    public abstract String toDataFileString();
}
