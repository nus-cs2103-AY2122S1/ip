package duke.task;

/**
 * An encapsulation of a Task to be done, that can be marked as completed.
 * @author Thomas Hogben
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @param description The description of this task.
     * @param isDone Whether this task has been completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * @param description The new description of this task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * @return Whether this task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * @return The status icon of this task. "X" if done; " " if undone.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * @return The description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The save string of this task. Incomplete, to be completed by child classes.
     */
    public String getSave() {
        return (this.isDone() ? "1" : "0") + "|" + this.getDescription();
    }

    /**
     * @return The display string of this task. Incomplete; to be completed by child classes.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
