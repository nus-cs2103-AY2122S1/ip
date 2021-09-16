package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements an Event object extending from Task.
 */
public class Event extends Task {
    protected final LocalDateTime dateTime;

    /**
     * Constructs an Event object.
     * @param description Description of the event.
     * @param dateTime Date and time of the event.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = convertDateTime(dateTime);
    }

    /**
     * Constructs an Event object.
     * @param description Description of the event.
     * @param dateTime Date and time of the event.
     * @param tag Tag associated with the event.
     */
    public Event(String description, String dateTime, String tag) {
        super(description);
        this.dateTime = convertDateTime(dateTime);
        this.tag = tag;
    }

    private LocalDateTime convertDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Returns the string representation of the event.
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return this.getTag().isEmpty()
                ? "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: "
                        + this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + ")"
                : "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + ") " + this.getTag();
    }
}
