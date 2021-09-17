package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    /** Date of event. */
    private final LocalDate date;

    /** Start time of event. */
    private final LocalTime start;

    /** End time of event. */
    private final LocalTime end;

    /**
     * Constructor of Event.
     *
     * @param description description of event.
     * @param date date of event.
     * @param start start time of event.
     * @param end end time of event.
     */
    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Parses the Event to a format appropriate for saving.
     *
     * @return a string containing only important information of the Event to be saved.
     */
    @Override
    public String parseToSave() {
        return "E|" + super.parseToSave() + "|" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "|" + start.format(DateTimeFormatter.ofPattern("h:mm a"))
                + "|" + end.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * toString method of Event.
     *
     * @return toString description of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", "
                + start.format(DateTimeFormatter.ofPattern("h:mm a"))
                + " - "
                + end.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
