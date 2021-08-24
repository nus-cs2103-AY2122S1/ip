package lania.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Time/place of an event */
    protected String at;
    protected LocalDateTime dateTime;

    /**
     * Constructor for lania.task.Event. Takes in a String description and at.
     * Initialises description and time of event.
     *
     * @param description The name of the event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(at, formatter);
    }

    public String getStringFormat() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}