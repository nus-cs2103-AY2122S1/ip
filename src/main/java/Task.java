public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the class `Task`.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task to a string.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String status = this.getStatusIcon();
        return String.format("[%s] %s", status, this.description);
    }
}
