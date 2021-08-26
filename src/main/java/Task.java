public abstract class Task {
    private String taskName;
    private TaskType taskType;
    private boolean isDone;

    public abstract String toSaveFormat();

    public Task(String taskName, TaskType taskType, boolean isDone) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public Task(String taskName, TaskType taskType) {
        this(taskName, taskType, false);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskType getTaskType() {
        return taskType;
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

;
