public class Task {
    protected String description;
    protected boolean isDone;
    protected int taskId;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskId = ++totalTasks;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return this.getStatus() + this.description;
    }
}
