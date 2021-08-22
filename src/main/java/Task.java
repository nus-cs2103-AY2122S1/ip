public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }
}
