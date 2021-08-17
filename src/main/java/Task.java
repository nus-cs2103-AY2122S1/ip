public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    protected void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
