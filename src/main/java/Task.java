public class Task {
    private String task;
    private boolean completed;
    private String taskType;

    public Task(String task) {
        this.task = task;
        this.completed = false;
        this.taskType = " ";
    }

    public Task(String task, String taskType) {
        this.task = task;
        this.completed = false;
        this.taskType = taskType;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getCompletedMarker() {
        return (this.completed? "X" : " ");
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean setCompleted() {
        this.completed = true;
        return this.completed;
    }

    public String getTask() {
        return this.task;
    }
}
