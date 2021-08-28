package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class inherits from Task that contains the name, the time and the completion status.
 */
public class Event extends Task {

    private String marker = "E";
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructor for an Event.
     * @param name Name of the Event.
     * @param time Time of the Event
     * @param isDone Completion status of the Event.
     */
    public Event(String name, LocalDateTime time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    /**
     * Returns the time in a specified format.
     * @return Time in dd/MM/yy HHmm.
     */
    @Override
    public String getTime() {
        return this.time.format(formatter);
    }

    /**
     * Returns the marker "E" denoting event.
     * @return A string "E".
     */
    @Override
    public String getMarker() {
        return this.marker;
    }

    /**
     * The string representation of an Event.
     * @return Event in string.
     */
    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy EEE HH:mm")) + ")";
    }
}
