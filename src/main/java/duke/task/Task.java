package duke.task;

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

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        assert description != null : "description variable should not be null";
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + " " + description;
    }

}
