public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String displayStatus() {
        return this.isDone ? "X" : " ";
    }

    public void complete() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
