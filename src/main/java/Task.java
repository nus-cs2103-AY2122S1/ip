public class Task {
    private String content;
    private boolean isDone;

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.content);
    }

    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
