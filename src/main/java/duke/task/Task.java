package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task that can be performed by the Duke program.
 */
public class Task {
    /** Formatter of input time */
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /** Description of the task */
    protected String description;
    /** Whether the task is done */
    protected boolean isDone;

    /**
     * Constructor of the class `Task`.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the current task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Converts the task to a string.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String status = this.getStatusIcon();
        return String.format("[%s] %s", status, this.description);
    }

    /**
     * Converts the task to a string with the format of the file in hard disk.
     *
     * @return String representation of the task in the file's format.
     */
    public String toFileFormatString() {
        return String.format("%s / %s\n", this.isDone ? "1" : "0", this.description);
    }
}
