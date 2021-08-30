package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task containing description and the time of the event.
 */
public class Event extends Task implements TimedItem {
    /** LocalDateTime of the event */
    private final LocalDateTime time;
    
    /**
     * Creates an Event that has a description and timing.
     *
     * @param desc String representing the description
     * @param time LocalDateTime.
     */
    public Event(String desc, LocalDateTime time) {
        super(desc);
        this.time = time;
    }
    
    /**
     * String representation of Event, marked with [E], desc and the timing
     *
     * @return string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "[E]" + super.toString() + " (at : " + time.format(formatter) + ")";
    }
    
    @Override
    public LocalDateTime getTime() {
        return time;
    }
}
