public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon, either "X" or "".
     * @return a String which represents the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get the status message of the task.
     * @return A formatted string containing the status and description of the task.
     */
    public String getStatusMessage() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Update status as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

}