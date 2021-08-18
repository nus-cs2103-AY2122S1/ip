public abstract class Task {
    private final String text;
    private boolean isDone;

    public Task(String text) {
        this.text = text;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    private String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    private String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatus(), getText());
    }
}