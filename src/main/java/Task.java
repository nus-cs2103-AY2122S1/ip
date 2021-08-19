public class Task {
    private String description;
    private boolean isDone;

    /**
     * Public Constructor for Task.
     * @param description the description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * return the string representation of the task status.
     * @return X if the task is done
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +
                "] " + this.description;
    }
}
