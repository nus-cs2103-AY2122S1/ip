package tasks;

public class Task {
    protected final String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public boolean getStatus() {
        // Mark done task with X
        return isDone;
    }

    public String markDone() {
        this.isDone = true;
        return this.toString();
    }

    public String getDescription() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "]  " + this.taskName ;
    }
}
