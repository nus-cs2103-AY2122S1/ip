package abyss.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a description and date.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param date Date of event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns formatted details of the event.
     *
     * @return Formatted event details.
     */
    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns details of the event formatted for file entry.
     *
     * @return Formatted event details.
     */
    @Override
    public String toFileEntry() {
        return "E | " + super.getIsDone() + " | " + super.description + " | " + this.date;
    }
}
