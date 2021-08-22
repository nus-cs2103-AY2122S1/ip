package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public enum TaskTypes {
        TODO, DEADLINE, EVENT
    }

    public static int numOfTasks = 0;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String checkbox = isDone ? "[X]" : "[ ]";
        return checkbox + " " + description;
    }
}
