package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a task. The input must be in such a format
 * "Event <event name> /at <event date>".
 */
public class Event extends Task {
    private final String EVENT = "[E]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     */
    public Event(String description, String dateAndTime) {
        super(description);
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
        assert localDateTime != null : "Date should not be null";
        return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: " + localDateTime.format(dtf) + ")";
    }

    /**
     * Returns new Task with the same description as this, but an opposite status.
     *
     * @return Copy of this task object.
     */
    @Override
    public Task getToggledDone() {
        Event toggledEvent = new Event(description, dateAndTime);
        if (!this.getDone()) {
            toggledEvent.setDone();
        }
        return toggledEvent;
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
