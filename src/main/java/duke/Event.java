package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to represent an Event task.
 */
public class Event extends Task {
    /**
     * The time and date the event will happen.
     */
    private LocalDateTime at;

    /**
     * Constructor for an Event object.
     *
     * @param description name of the task
     * @param at the date and time the event happens
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event information
     *
     * @return the event information
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Checks if the Task is an EmptyTask.
     *
     * @return true if this Task is an EmptyTask, false otherwise
     */
    @Override
    public boolean isEmptyTask() {
        return false;
    }
}
