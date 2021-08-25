package tasks;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    abstract public String format();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}