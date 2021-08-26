package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Get the done status of task
     *
     * @return X if task is done, empty space if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides toString method
     * @return description of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
