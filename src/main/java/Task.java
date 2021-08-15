public class Task {
    // fields of each task
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Check if the task is completed and returns the corresponding icon.
     *
     * @return A String representing the status of the task.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Mark the task has been completed.
     */
    public void taskCompleted() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return A String representing the description of the task.
     */
    public String getDescription() {
        return description;
    }
}
