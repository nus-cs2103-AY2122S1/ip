public abstract class Task {
    private final String content;
    private boolean isDone;

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public Task(String content) {
        this(content, false);
    }

    public abstract String dataFormat();

    protected int isDoneInt() {
        return isDone ? 1 : 0;
    }

    String getContent() {
        return this.content;
    }

    void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}