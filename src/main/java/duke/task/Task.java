package duke.task;

/**
 * Represents a Task object that support serializing to database string and pretty print. Can be marked as completed.
 */
abstract public class Task {
    protected String descriptions;
    protected boolean isDone;

    public Task(String descriptions) {
        this.descriptions = descriptions;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    abstract String getTaskType();

    public Task done() {
        this.isDone = true;
        return this;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescriptions() {
        return this.descriptions;
    }

    /**
     * Returns | delimited string that is used to represent the task.
     * Used for serializing task for database.
     *
     * @return String serialized for database.
     */
    public String toDatabaseString() {
        return String.format("%s|%d|%s",
                this.getTaskType(), this.isDone() ? 1 : 0, this.getDescriptions());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.descriptions);
    }
}
