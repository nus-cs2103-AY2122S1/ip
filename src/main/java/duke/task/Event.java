package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h2>Event</h2>
 * A task which has an additional <code>dateTime</code> field which informs the user when this event occurs.
 * @see Deadline
 * @see ToDo
 */

public class Event extends Task {

    private final LocalDateTime dateTime;

    public Event(String eventName, LocalDateTime dateTime) {
        super(eventName);
        this.dateTime = dateTime;
    }

    private Event(Event oldEvent) {
        super(oldEvent);
        this.dateTime = oldEvent.dateTime;
    }
    
    public static Event createTask(String name, boolean isCompleted, LocalDateTime dateTime) {
        Event e = new Event(name, dateTime);
        if (isCompleted) {
            return new Event(e);
        } else {
            return e;
        }
    }

    /**
     * {@inheritDoc}
     * @return a new <code>Event</code> which is exactly the same except with completion status set to 
     * <code>true</code>.
     */
    @Override
    public Event markAsCompleted() {
        return new Event(this);
    }

    @Override
    public String toString() {
        return "E: " + super.toString() + " on: " + this.dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy," +
                " HH:mm"));
    }
}
