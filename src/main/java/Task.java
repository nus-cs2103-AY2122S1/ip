public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");// Mark done task with X
    }

    public String doTask() {
        this.isDone = true;
        return this.toString();
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "]  " + this.taskName ;
    }
}
