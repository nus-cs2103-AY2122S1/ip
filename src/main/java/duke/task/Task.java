package duke.task;

/**
 * Abstract Task class.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Queries whether the Task is done.
     *
     * @return boolean isDone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon depending on whether the Task is done.
     *
     * @return String Representation.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns ths string representation of Task.
     *
     * @return Representation of Task.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }

}
