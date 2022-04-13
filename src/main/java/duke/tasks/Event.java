package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    protected final LocalDateTime at;

    /**
     * The constructor for the Event.
     * @param description Description of the event.
     * @param at The date and time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    public LocalDateTime getTimeAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
