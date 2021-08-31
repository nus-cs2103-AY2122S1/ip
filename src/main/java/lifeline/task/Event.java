package lifeline.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The class Event encapsulates an event with a date, start time and end time.
 */
public class Event extends Task {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    /**
     * Default constructor for an Event.
     *
     * @param name Name of the event.
     * @param date Date of the event.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     */
    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor to be used when loading saved Event.
     *
     * @param name Name of the event.
     * @param date Date of the event.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     * @param isDone Indicates whether the event is done.
     */
    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isDone) {
        super(name, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        return "[E]" + super.toString() + " (at: " + dateString + " " + startTimeString + " - " + endTimeString + ")";

    }
}
