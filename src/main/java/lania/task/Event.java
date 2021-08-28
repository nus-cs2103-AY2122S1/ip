package lania.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task class as an event.
 */
public class Event extends Task {

    /** Date and time of an event */
    protected String at;
    /** Formatted date and time of a task */
    protected LocalDateTime dateTime;

    /**
     * Constructor for Event which consists of description and time of task.
     *
     * @param description The name of the deadline.
     * @param at The date and time of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(at, formatter);
    }

    /**
     * Converts the event object to a string representation.
     *
     * @return The string representation of the event object.
     */
    public String getStringFormat() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.at + "\n";
    }

    /**
     * Another string representation of the event object.
     *
     * @return A string representation of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}