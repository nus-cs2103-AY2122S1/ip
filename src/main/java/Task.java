/**
 * This class represents a Task object, which has a description for the current task and also a boolean value which
 * indicates if the task is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Outputs "X" if the task is done, else output " ".
     * @return A String which represents whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Provides a String representation for each Task object.
     * @return A String that describes the current Task object.
     */
    public abstract String toString();

    /**
     * Sets whether the current Task is done or not.
     * @param value Boolean value to set isDone to.
     */
    public void setIsDone(boolean value) {
        this.isDone = value;
    }
}
