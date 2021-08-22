package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
