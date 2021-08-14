public class Task {
    private final String content;
    private final boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return this.content;
    }
}