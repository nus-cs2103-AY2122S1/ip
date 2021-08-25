public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toSaveData() {
        return this.isDone + "|" + this.taskName;
    }

    @Override
    public String toString() {
        String statusString = this.isDone ? "X" : " ";
        return String.format("[%s] %s", statusString, this.taskName);
    }
}
