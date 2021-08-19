public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        ++totalTasks;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return this.getStatus() + this.description;
    }
}
