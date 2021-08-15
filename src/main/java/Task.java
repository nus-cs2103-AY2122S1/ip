/**
 * This is the Task class.
 * A Task contains a description and a status of being done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws AisuException {
        if (description.length() == 0) {
            throw new AisuException("The description cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[x]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toString();
}
