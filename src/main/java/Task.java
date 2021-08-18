import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
    }

    public String getTaskType() {
        return this.taskType;
    }

}