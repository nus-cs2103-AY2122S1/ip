package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.isDone = completed;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String format() {
        return this.toString();
    }

    @Override
    public String toString() {
        String res = "[" + getStatus() + "] " + description;

        return res;
    }
}
