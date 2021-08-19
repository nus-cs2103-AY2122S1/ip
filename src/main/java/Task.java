public class Task {
    protected String description;
    protected boolean isDone;
    protected int taskId;
    private static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskId = ++totalTasks;
    }

    public String getStatus() {
        String statusIcon = isDone ? "[X] " : "[ ] ";
        return (statusIcon + this); // mark done task with X
    }

    @Override
    public String toString() {
        return this.description;
    }
}
