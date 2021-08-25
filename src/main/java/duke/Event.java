package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a task. The input must be in such a format
 * "Event <event name> /at <event date>".
 */
public class Event extends Task{
    protected String description;
    protected boolean isDone;
    final String EVENT = "[E]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Public constructor for an event.
     *
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns the date and time of the event in a string.
     *
     * @return Returns the date and time of the event.
     */
    public String getDate() {
        return this.dateAndTime;
    }

    /**
     * Formats the date and time in order to be
     * parsed into the DateTimeFormatter.
     */
    public void formatLocalDateTime() {
        if (this.dateAndTime.substring(0, 1).matches("[0-9]")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.localDateTime = LocalDateTime.parse(dateAndTime, dateTimeFormatter);
        } else {
            this.localDateTime = LocalDateTime.parse(dateAndTime, dtf);
        }
    }

    /**
     * Returns the string of the event.
     *
     * @return String of event.
     */
    @Override
    public String toString() {
        formatLocalDateTime();
        return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: " + localDateTime.format(dtf) + ")";
    }

    /**
     * Checks to see if two events are equal in description and status.
     * Returns false if object is not equal to this event.
     *
     * @param object Object compared to.
     * @return Boolean true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event event = (Event) object;
            return (event.getDone() == this.getDone()) && event.description.equals(this.description);
        }
        return false;
    }
}
