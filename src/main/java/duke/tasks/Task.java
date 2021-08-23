package duke.tasks;

public class Task {
    
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getSaveIcon() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toSaveString() {
        return this.getSaveIcon() + " | " + this.description;
    }
}