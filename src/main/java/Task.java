/**
 * An encapsulation of a Task to be done, that can be marked as completed.
 * @author Thomas Hogben
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    public String getSave() {
        return (this.isDone() ? "1" : "0") + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
