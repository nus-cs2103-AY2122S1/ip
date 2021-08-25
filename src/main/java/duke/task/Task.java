package duke.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }

    public abstract String getRecordString();
}
