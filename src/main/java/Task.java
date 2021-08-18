public class Task {
    String content;
    boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return String.format("[T] [%s] %s", isDone ? "X" : " ", content);
    }
}
