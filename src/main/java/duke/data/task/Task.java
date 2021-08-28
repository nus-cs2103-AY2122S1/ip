package duke.data.task;

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

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    public String formatToWrite() {
        return String.format("%d | %s", (this.isDone ? 1 : 0), this.description);
    }

    public boolean isDone() {
        return isDone;
    }
}
