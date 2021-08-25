package Duke.Task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    enum TaskType {
        D,
        E,
        T
    }

    protected Task() {
        this.description = "NONE";
        this.isDone = false;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusInt() {
        return (isDone ? "1" : "0");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.description);
    }

    public String getDataString() {
        return String.format("%s_~_%s_~_%s", this.taskType, this.getStatusInt(), this.description);
    }
}
