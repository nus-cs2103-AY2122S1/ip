public class Task {
    private final String text;
    private boolean isDone;

    public Task(String text) {
        this.text = text;
        this.isDone = false;
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatus(), getText());
    }
}