public abstract class Task {

    private final String description;
    private boolean isDone;
    private final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String getDescriptionWithStatus() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}
