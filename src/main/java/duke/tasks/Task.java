package duke.tasks;

/**
 * Represents a task for the user to do.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for a task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] :  %s", getStatusIcon(), description);
    }

    public String getData() {
        int isDoneBinary = isDone ? 1 : 0;
        return String.format("%d | %s", isDoneBinary, description);
    }
}
