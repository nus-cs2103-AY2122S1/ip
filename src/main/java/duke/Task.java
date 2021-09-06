package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task in a task list.
 */
public class Task {

    protected enum TaskType {
        todo, deadline, event
    }
    protected TaskType category;
    private final String description;
    private final LocalDate time;
    private boolean completed;

    /**
     * Constructs a Task with a specified time.
     *
     * @param description String description of task.
     * @param time Time scheduled for task.
     */
    public Task(String description, LocalDate time) {
        this.description = description;
        this.time = time;
        completed = false;
    }

    /**
     * Marks a task as completed.
     */
    public void setCompleted() {
        completed = true;
    }

    /**
     * Gets the status of a task as a checkbox.
     *
     * @return A string representing status of task.
     */
    private String getStatus() {
        return "[" + (completed ? "X" : " ") + "]";
    }

    /**
     * Gets a string representation of the time of the task.
     *
     * @return String representing time of task.
     */
    protected String getTimeString() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Checks if a task is scheduled for a certain date.
     *
     * @param time Date to be checked for.
     * @return Boolean describing if task is scheduled for that date.
     */
    public boolean isScheduledFor(LocalDate time) {
        return this.time.equals(time);
    }

    /**
     * Returns string representation of task.
     *
     * @return String representing task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }
}
