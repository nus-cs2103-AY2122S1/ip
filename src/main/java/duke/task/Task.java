package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    /**
     * Constructs a task.
     *
     * @param description
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.getStatusIcon(), this.priority, this.description);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String convertToFileFormat() {
        return String.format(" | %s | %s | %s", this.isDone ? "1" : "0", this.priority, this.description);
    }
}
