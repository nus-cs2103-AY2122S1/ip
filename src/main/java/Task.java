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
        isDone = true; // mark this task as done
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
