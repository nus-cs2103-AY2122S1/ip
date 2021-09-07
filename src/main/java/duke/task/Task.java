package duke.task;

/**
 * This class represents a duke.task.Task object, which has a description for the current task and also a boolean value which
 * indicates if the task is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the duke.task.Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Outputs "X" if the task is done, else output " ".
     *
     * @return A String which represents whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Provides a String representation for each duke.task.Task object.
     *
     * @return A String that describes the current duke.task.Task object.
     */
    public abstract String toString();

    /**
     * Sets whether the current duke.task.Task is done or not.
     *
     * @param value Boolean value to set isDone to.
     */
    public void setIsDone(boolean value) {
        this.isDone = value;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Increases the date of the task by a set number of days.
     *
     * @param days Number of days to increase by.
     */
    public abstract void increaseDateByDays(int days);
}
