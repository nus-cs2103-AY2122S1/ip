package blitz.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime date;

    /**
     * Creates an Event based on the given description, date and time.
     *
     * @param description mentions what the event is.
     * @param date date and time of event.
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: "
            + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

}
