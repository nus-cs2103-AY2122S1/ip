public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        isDone = false;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", taskName);
    }

    public void markDone() {
        isDone = true;
    }
}
