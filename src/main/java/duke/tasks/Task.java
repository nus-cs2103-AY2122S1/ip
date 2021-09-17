package duke.tasks;

/**
 * A Task class encapsulates the data related to a task
 * ie task description and status.
 *
 * @author Lethicia
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a duke.task.Task object.
     *
     * @param description the title or description for the task
     */
    public Task(String description) {
        this.description = description;

        /* task marked as incomplete by default.*/
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return X to mark task as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the file's details in the format "<isDone>,<desc>"
     * to be stored in the hard disk.
     *
     * @return formatted string containing task details
     */
    public abstract String getText();

    /**
     * Represents the task description and status
     *
     * @return details of the task in a given format
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
