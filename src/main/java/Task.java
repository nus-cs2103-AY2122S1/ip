public class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String taskType) {
        this(description);
        this.taskType = taskType;
    }

    public Task(String description, String taskType, String time) {
        this(description, taskType);
        this.time = time;
    }

    protected String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public void markFinished(boolean t){
        this.isDone = t;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
