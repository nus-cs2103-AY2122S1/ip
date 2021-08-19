public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void taskDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String taskStr = String.format("[%s] ", getStatusIcon())
                + this.description;
        return taskStr;
    }
}
