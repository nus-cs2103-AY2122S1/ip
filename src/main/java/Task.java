public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatus() {
        // Mark done task with X
        return isDone ? "X" : " ";
    }

    public String markDone() {
        this.isDone = true;
        return this.toString();
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "]  " + this.taskName ;
    }
}
