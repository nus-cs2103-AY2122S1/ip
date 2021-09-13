package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return if the task is marked as done.
     */
    public boolean getIsDone() { return this.isDone;}

    /**
     * Retrieves the description of the task instance.
     *
     * @return the string description of the task
     */
    public String getDescription() { return this.description; }
    public String getPriority() { return this.priority;}

    @Override
    public String toString() {
       return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}