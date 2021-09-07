package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is an extension of Task, and includes a start time for the event when the user specifies it.
 */
public class Event extends Task {

    private String startTime;

    /**
     * Initialises an Event.
     *
     * @param str String that tells us the description.
     * @param startTime The starting time of the event.
     */
    public Event(String str, LocalDateTime startTime) {
        super(str, startTime);
        DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm");
        this.startTime = dateOnly.format(startTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + startTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return this.startTime.equals(other.startTime);
    }
}
