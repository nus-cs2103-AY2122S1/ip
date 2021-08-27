package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Date of the Event */
    protected LocalDate date;
    /** Time of the Event. */
    protected LocalTime time;

    /**
     * Constructor for Event.
     * @param description Text description of Event.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Override toString method to show representation of Event.
     * @return String representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
