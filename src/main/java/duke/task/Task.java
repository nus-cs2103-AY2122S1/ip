package duke.task;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task object.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task (X for done, " " for not yet done).
     *
     * @return the icon that represents the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a boolean representing the status of the task.
     *
     * @return the task's isDone value.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Updates the description and due date of the Event.
     *
     * @param input the description of the event task.
     * @return new Task with updated description.
     */
    public abstract Task update(String input);

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
