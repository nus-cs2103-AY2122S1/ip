package jared.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts to string for printing list.
     * @return String of task details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Converts to string for saving in data.
     * @return String of task details in save format.
     */
    public String saveFormat() {
        return String.format("%d _ %s", this.isDone ? 1 : 0, this.description);
    }
}
