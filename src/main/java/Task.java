public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String statusString = this.isDone ? "X" : " ";
        return String.format("[%s] %s", statusString, this.taskName);
    }
}
