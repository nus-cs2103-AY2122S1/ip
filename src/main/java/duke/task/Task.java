package duke.task;

import java.time.LocalDate;

/**
 * Represents a general Task with a description and a 'done' status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The constructor for a Task object.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an 'X' when the Task is done and a blank space
     * if it is not.
     *
     * @return The appropriate symbol depending on Task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the Task.
     *
     * @return The Task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the status of the Task
     *
     * @return True if the Task is done, else False.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Retrieves the date of the Task, if applicable.
     *
     * @return If it is not a Deadline or Event, returns null.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Marks the Task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Converts the Task into the specified format for
     * the text file from Storage.
     *
     * @return The file format representation of the Task.
     */
    public String toFileFormat() {
        return "";
    }

    /**
     * Returns a string representation of the Task. Appends the
     * 'done' status at the start.
     *
     * @return A string representation of the Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
