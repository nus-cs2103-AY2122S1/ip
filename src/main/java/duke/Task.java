package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }
}