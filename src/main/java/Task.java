public abstract class Task {
    private final String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    void setDone() {
        this.isDone = true;
    }

    String getContent() {
        return this.content;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}