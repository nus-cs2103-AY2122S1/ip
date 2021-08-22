package botto.task;

public class TaskStub extends Task{
    public String description;
    public boolean isDone;

    public TaskStub(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
