public class Task {
    private String taskName;
    private String taskType;
    private boolean isDone;

    public Task(String taskName, String taskType, boolean isDone) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public Task(String taskName, String taskType) {
        this(taskName, taskType, false);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String checked = isDone ? "X" : " ";
        return String.format("[%s] %s", checked, taskName);
    }
}
