package eightbit.task;

public class TaskStub extends Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a stub that represents a task.
     *
     * @param description Description of task.
     * @param isDone      Is the task completed or not.
     */
    public TaskStub(String description, boolean isDone) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
