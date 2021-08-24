package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getType();

    public abstract String getDescription();

    public abstract String getDateTimeString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
