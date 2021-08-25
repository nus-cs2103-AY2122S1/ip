package duke.task;

public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    public void complete() {
        this.isDone = true;
    }

    public String getType() {
        return "T";
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String displayInfo() {
        return String.format("[T] [%s] %s", this.getStatus(), this.getTaskName());
    }

    public String getSaveInfo() {
        if (this.isDone()) {
            return String.format("T | 1 | %s", this.getTaskName());
        } else {
            return String.format("T | 0 | %s", this.getTaskName());
        }
    }

}
