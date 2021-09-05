package duke.task;

/**
 * Represents a Task object that support serializing to database string and pretty print. Can be marked as completed.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Construct a Task object
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    abstract String getTaskType();

    /**
     * Mark task as done.
     *
     * @return Updated Task object.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns | delimited string that is used to represent the task.
     * Used for serializing task for database.
     *
     * @return String serialized for database.
     */
    public String toStorageString() {
        return String.format("%s|%d|%s",
                this.getTaskType(), this.isDone() ? 1 : 0, this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            return ((Task) other).description.equals(this.description);
        }
        return false;
    }
}
