public class Task {
    protected String content;
    protected boolean done;
    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    public void finishTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X":" ", this.content);
    }
}
