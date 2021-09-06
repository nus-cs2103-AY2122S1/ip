package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a Task with a date.
 */
public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTime;

    /**
     * Initialises an Event with a description and date of the event.
     *
     * @param description a description String for Event.
     * @param atDate the date of the Event.
     */
    public Event(String description, LocalDate atDate) {
        super(description);
        this.atDate = atDate;
    }

    /**
     * Initialises an Event with a description and date and time of the event.
     *
     * @param description a description String for Event.
     * @param atDate the date of the Event.
     * @param atTime the time of the Event.
     */
    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    public LocalDate getDate() {
        return atDate;
    }

    @Override
    public String toString() {
        if (atTime != null) {
            return "[E]" + super.toString() + " (at: "
                    + atDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + ", " + atTime + ")";
        }
        return "[E]" + super.toString() + " (at: "
                + atDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
