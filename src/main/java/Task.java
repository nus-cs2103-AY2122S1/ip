public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public Task markDone() {
        Task task = new Task(this.description);
        task.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return this.getStatusIcon()  + this.description;
    }
}
