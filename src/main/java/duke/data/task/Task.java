package duke.data.task;

/**
 * Class that represents a task.
 *
 * @author Wang Hong Yong
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the done status of the task.
     * @return string representation of the done status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Formats the task to the desirable format.
     *
     * @return A string representing the task in the desirable format.
     */
    public String formatToWrite() {
        return String.format("%d | %s", (this.isDone ? 1 : 0), this.description);
    }

    /**
     * Returns the boolean representation of the completion of task.
     *
     * @return boolean representation of the task completion.
     */
    public boolean isDone() {
        return isDone;
    }
}
