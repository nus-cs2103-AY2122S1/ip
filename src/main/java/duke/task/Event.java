package duke.task;

import duke.Duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <code>Event</code> subclass includes two LocalTime attributes "startTime" and "endTime"
 * to indicate the time range of the Event object
 * The [E] in toString() identifies an Event object
 */

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isDone) {
        //fixed date
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime =  endTime;

    }

    @Override
    public String toString() {
        return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (at: " + this.date + " " + this.startTime + " to " + this.endTime + ")";
    }
}
