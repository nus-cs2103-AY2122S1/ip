package sora.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 * An event contains extra information regarding the date, as well as start and end time of the event.
 *
 * @author Zhang Shi Chen
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructor for event.
     *
     * @param description Description of the event
     * @param date Date of the event
     * @param startTime Start time of the event
     * @param endTime End time of the event
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    LocalDateTime getDateTime() {
        return LocalDateTime.of(date, startTime);
    }

    /**
     * Formats event in the form of: [E][ ] description (at: MMM d yyyy, h:mm a - h:mm a)
     *
     * @return A string representation of the event
     */
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + startTime.format(timeFormatter) + " - "
                + endTime.format(timeFormatter) + ")";
    }
}
