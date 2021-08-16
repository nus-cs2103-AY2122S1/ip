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

    public String displayInfo() {
        return String.format("[T] [%s] %s", this.getStatus(), this.getTaskName());
    }
}
