package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This Event class implements the characteristics of a task
 * that is held on a specific date.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for an Event instance that takes in a description and date.
     *
     * @param description The given task description.
     * @param at The date on which the event is held.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date on which the event is held.
     *
     * @return A LocalDate instance representing the event date.
     */
    public LocalDate getDate() {
        return this.at;
    }

    /**
     * Returns the string representation of an Event instance.
     *
     * @return A string representing an Event instance.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
