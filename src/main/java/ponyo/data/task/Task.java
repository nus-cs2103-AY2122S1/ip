package ponyo.data.task;

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

    // File operations
    public int getStatusNumber() {
        return (isDone ? 1 : 0); // mark done task with 1
    }

    // Sample: D - 0 - return book - June 6th
    public String toStringInFile() {
        return String.format("%d - %s", this.getStatusNumber(), this.description);
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public boolean containsKeyword(String kw) {
        return description.contains(kw);
    }
}