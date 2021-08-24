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
        isDone = true;
    }

    public String toData() {
        return (isDone ? "1|" : "0|") + this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
