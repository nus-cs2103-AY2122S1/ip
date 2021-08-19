public abstract class Task {
    protected TaskType taskType;
    protected String taskDescription;
    protected boolean isDone;

    public Task(TaskType taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + taskDescription;
    }
}
