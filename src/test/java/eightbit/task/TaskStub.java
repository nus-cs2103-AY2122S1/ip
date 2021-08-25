package eightbit.task;

public class TaskStub extends Task {

    public String description;
    public boolean isDone;

    public TaskStub(String description, boolean isDone) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
