package sun.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that represents a task.
 *
 * @author Won Ye Ji
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd h:mm a");

    /**
     * Constructor for Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description is empty!";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the done status of the task.
     *
     * @return String representation of the done status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Formats the event date to the desirable format.
     *
     * @param at Input date format.
     * @return A string representing the date in the desirable format.
     */
    public String formatDate(String at) throws DateTimeParseException {
        try {
            LocalDateTime date = LocalDateTime.parse(at, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return "Format error";
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public abstract String toSave();
}
