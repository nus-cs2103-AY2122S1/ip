public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName.trim();
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getFileString() {
        return String.format("%d | %s", isDone ? 1 : 0, this.taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.taskName);
    }
}
