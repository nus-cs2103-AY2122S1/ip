package duke;

/**
 * A general task that a user can input.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of the event.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if a task is done, or not.
     *
     * @return Particular string if task is done, or not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets boolean if task is done, or not.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Checks if a task is done, or not.
     *
     * @return True if task is done, false otherwise.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * The description of a Task.
     *
     * @return String of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string of the task.
     *
     * @return String of event.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public abstract Task getToggledDone() throws DukeException;

    /**
     * Checks to see if two tasks are equal in description and status.
     * Returns false if object is not equal to this task.
     *
     * @param object Object compared to.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            Task task = (Task) object;
            return task.getDone() == this.getDone() && task.description.equals(this.description);
        }
        return false;
    }
}
