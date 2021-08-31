package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Container for a task item.
 */
public class Task {
    /**
     * Describes the task item.
     */
    protected String description;

    /**
     * Formatter for parsing string into date.
     */
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    /**
     * True if the task item has been completed; false otherwise.
     */
    protected boolean isDone;

    /**
     * Instantiates a Task object
     *
     * @param description Description of the task item
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Instantiates a Task Object.
     *
     * @param description Description of the task item.
     * @param isDone True if the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Displays if the task has been done.
     *
     * @return "X" for a completed task; " " for an incomplete one.
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task item as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of the task item.
     *
     * @return String representation of the task item.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of the task to be saved in the hard disk.
     *
     * @return String to be saved in the hard disk.
     */
    public String toSaveString() {
        return "|" + (isDone ? "1" : "0") + "|" + this.description;
    }
}