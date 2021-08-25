package nyx;

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

    protected String getContent() {
        return this.content;
    }

    public void setDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}