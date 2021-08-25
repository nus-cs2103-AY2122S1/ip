package duke.command.task;

public abstract class Task {
    private final String text;
    private boolean isDone;

    public Task(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    private String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    public int getDoneInt() {
        return  this.isDone ? 1 : 0;
    }

    public String getText() {
        return this.text;
    }

    abstract String getSaveFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatus(), getText());
    }
}