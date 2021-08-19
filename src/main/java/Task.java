public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }
    public String check() {
        this.isDone = true;
        return this.description;
    }

    public String toString() {
        return getStatusIcon() + description;
    }
}
