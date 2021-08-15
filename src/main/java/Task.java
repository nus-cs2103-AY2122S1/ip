/**
 * This is the Task class.
 * A Task contains a description and a status of being done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "☑" : "☐");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toString();
}
