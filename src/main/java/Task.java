public class Task {
    private String taskName;
    private boolean status;
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    protected void markAsCompleted() {
        this.status = true;
    }

    protected boolean getStatus() {
        return this.status;
    }
    protected String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return task.taskName.equals(this.taskName);
        } else {
            return false;
        }
    }
}
