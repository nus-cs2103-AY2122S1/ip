package bloom.task;

import java.time.LocalDateTime;

/**
 * Represents a general task.
 */
public abstract class Task {

    /** The description of the task. */
    protected final String description;

    /** The status of the task. */
    protected boolean isDone;

    protected String note;

    /**
     * Constructor for a Task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.note = null;
    }

    /**
     * Gets the string representation of the task.
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return "["
                + this.getStatusIcon() + "] "
                + this.description
                + (this.note == null ? "" : " (" + this.note + ")");
    }

    /**
     * Converts to the format used for database storage.
     *
     * @return a string representing the task
     */
    public String toDb() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return X if the task is done and a space otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public abstract LocalDateTime getDate();

    /**
     * Prints date of a task.
     *
     * @return a string date of format MMM dd yyyy
     */
    protected abstract String printDate();
}
