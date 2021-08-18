/**
 * An individual task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Return the completion status of the task
     *
     * @return Task completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }

}
