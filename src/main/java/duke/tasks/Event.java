package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Container for an event task.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Instantiates an Event
     *
     * @param description Description of the event
     * @param at Scheduled date of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Instantiates an Event object.
     *
     * @param description Description of the task.
     * @param isDone True if the task has been completed.
     * @param at Date when the task is scheduled at.
     */
    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    public LocalDate getAtDate() {
        return this.at.toLocalDate();
    }

    /**
     * Returns the string representation of this Event object.
     *
     * @return String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(outputFormatter) + ")";
    }

    /**
     * Returns the string representation of the task to be saved in the hard disk.
     *
     * @return String to be saved in the hard disk.
     */
    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + "|" + this.at.format(outputFormatter);
    }
}
