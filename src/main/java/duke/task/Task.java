package duke.task;

public class Task {
    private String taskName;
    private boolean isDone;
    
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.taskName : "[] " + this.taskName;
    }
}
