/**
 * A class to represent a task to be done.
 */
public class Task {
    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Boolean to show whether this task has been completed.
     */
    protected boolean isDone;

    /**
     * Constructor to instantiate a Task object
     *
     * @param description description of the task item
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to display if the task has been done.
     *
     * @return "X" for a completed task; " " for an incomplete one.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * A method to mark the task item as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * A method to return the string representation of the task item.
     *
     * @return String representation of the task item.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}