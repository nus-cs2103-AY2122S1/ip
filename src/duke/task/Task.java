package duke.task;

public class Task {
    protected String description;
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
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String populateSaveData() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }
}