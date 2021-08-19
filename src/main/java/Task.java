public class Task {
    private String description;
    private boolean isDone;
    private TaskType taskType;
    private String dateTime;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String description, TaskType taskType, String time) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.dateTime = time;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTypeIcon() {
        if (this.taskType == TaskType.TODO) {
            return "T";
        } else if (this.taskType == TaskType.DEADLINE) {
            return "D";
        } else {
            return "E";
        }
    }

    public String getDescription() {
        return description;
    }

    public void taskDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String taskStr = String.format("[%s][%s] ", getTypeIcon(), getStatusIcon())
                + this.description;
        if (this.dateTime != null) {
            taskStr += " (" + this.dateTime + ")";
        }
        return taskStr;
    }
}
