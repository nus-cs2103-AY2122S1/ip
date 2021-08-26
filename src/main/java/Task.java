public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String by;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String description, String taskType, String by) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.by = by;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
