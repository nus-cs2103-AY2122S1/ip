public abstract class Task {
    private String taskName;
    private boolean status;

    public enum taskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    protected void markAsCompleted() {
        this.status = true;
    }

    @Override
    public String toString() {
        if (status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
