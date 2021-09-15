package duke.task;

import duke.DukeException;

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
    public Task(String description) throws DukeException {
        setDescription(description);
        isDone = false;
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
    public void setDescription(String description) throws DukeException {
        if (description.contains("|")) {
            throw DukeException.INVALID_CHARACTER;
        }
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * @return Whether this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @return The status icon of this task. "X" if done; " " if undone.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * @return The save string of this task. Incomplete, to be completed by child classes.
     */
    public String getSave() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * @return The display string of this task. Incomplete; to be completed by child classes.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
