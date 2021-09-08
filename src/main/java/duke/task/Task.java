package duke.task;

/**
 * An encapsulation of a Task to be done, that can be marked as completed.
 * @author Thomas Hogben
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task() {
        description = "";
        isDone = false;
    }

    /**
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }

    public String getSave() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
