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
        return this.toString();
    }

    public String toString() {
        return getStatusIcon() + description;
    }

    public String toSaveString() {
        return isDone ? "1" : "0" + " | " + this.description;
    }
}
