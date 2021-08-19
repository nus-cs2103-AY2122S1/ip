/**
 * An encapsulation of a Task to be done, that can be marked as completed.
 * @author Thomas Hogben
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
