package duke;

public abstract class Task {
    private String task;
    private boolean isCompleted;
    private String taskType;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
        this.taskType = " ";
    }

    public Task(String task, String taskType) {
        this.task = task;
        this.isCompleted = false;
        this.taskType = taskType;
    }

    public Task(String task, boolean isCompleted, String taskType) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.taskType = taskType;
    }


    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public String getCompletedMarker() {
        return (this.isCompleted ? "X" : " ");
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean setCompleted() {
        this.isCompleted = true;
        return this.isCompleted;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String parseForStorage();
}
