package duke.task;

import duke.error.DukeException;

/**
 * Represents a task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task object.
     *
     * @param description Description of the task.
     * @param isDone Status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task() {

    }

    /**
     * Returns the done status of the task.
     *
     * @return X is task is done. Otherwise, returns a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the status of the task to done.
     */
    public void updateStatus() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Change the description.
     *
     * @param newDescription The new description.
     * @throws DukeException If newDescription is empty.
     */
    public void setDescription(String newDescription) throws DukeException {
        if (newDescription.equals("")) {
            throw new DukeException("OOPS!! description cannot be empty");
        }

        description = newDescription;
    }

    /**
     * Returns the task type.
     *
     * @return Task type.
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "] " + this.description;
    }
}
