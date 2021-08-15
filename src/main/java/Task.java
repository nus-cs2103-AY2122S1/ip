/**
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
