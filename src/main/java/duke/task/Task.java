package duke.task;

public class Task {
    /** The description for the task */
    protected String description;
    /** The completion status of the task */
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String toDataString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, this.description);    
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
