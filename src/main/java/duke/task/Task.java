package duke.task;

/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Show whether a task is done.
     *
     * @return String representation of the status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Yayyyy done yayyy~~");
        System.out.println(toString());
    }

    /**
     * Check whether a task is done.
     *
     * @return True if it is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * String representation of a task.
     *
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        return ( "|" + getStatusIcon() + "| "+ this.description);
    }

    /**
     * String format of a task that will be stored to the file.
     *
     * @return String to be stored.
     */
    public String toStoredString() {
        return "";
    }

}