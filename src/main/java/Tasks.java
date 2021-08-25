public class Tasks {
    protected String description;
    protected boolean isDone;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task (X if done, blank if not done).
     * @return the status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}