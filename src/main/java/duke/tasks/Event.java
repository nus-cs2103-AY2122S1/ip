package duke.tasks;

import java.time.LocalDateTime;

/**
 * Container for an event task.
 */
public class Event extends Task {

    /**
     * Instantiates an Event
     *
     * @param description Description of the event
     * @param date Scheduled date of the event
     */
    public Event(String description, LocalDateTime date) {
        super(description, date);
    }

    /**
     * Instantiates an Event object.
     *
     * @param description Description of the task.
     * @param isDone True if the task has been completed.
     * @param date Date when the task is scheduled at.
     */
    public Event(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone, date);
    }

    /**
     * Returns the string representation of this Event object.
     *
     * @return String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(outputFormatter) + ")";
    }

    /**
     * Returns the string representation of the task to be saved in the hard disk.
     *
     * @return String to be saved in the hard disk.
     */
    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + "|" + date.format(outputFormatter);
    }
}
