package duke.tasks;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    protected boolean isDone;

    /**
     * Instantiates a Task object
     *
     * @param description description of the task item
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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
        return (isDone ? "X" : " "); // mark done task with X
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

    public String toSaveString() {
        return "|" + (isDone ? "1" : "0") + "|" + this.description;
    }
}