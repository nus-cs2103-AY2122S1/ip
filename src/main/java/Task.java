/**
 * Represents a Task which contains a String description and a boolean status.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise a new Task.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to obtain the status icon of the particular task.
     * 
     * @return The status icon depending on whether the task is marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Method to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overridden toString method.
     * 
     * @return The String representation of the task, prefixed with a status icon.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
