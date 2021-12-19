package taubot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. A <code>Event</code> object corresponds to
 * a task represented by a date and time.
 */
public class Event extends Task {

    protected final LocalDate date;
    protected final String time;
    /**
     * Returns a <code>Taubot</code> object that can reply to
     * commands. Taubot can save the tasks at the specified <code>filePath</code>.
     *
     * @param description The description of the event task.
     * @param ld The <code>LocalDate</code> object to represent
     *           the date of the event.
     * @param time The time that the event is happening.
     */
    public Event(String description, LocalDate ld, String time) {
        super(TaskType.EVENT, description);
        this.date = ld;
        this.time = time;
    }
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns string representation of <code>Event</code> object.
     *
     * @return String representation of <code>Event</code> object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy "))
                + this.time + ")";
    }

    /**
     * Returns at property of Event object.
     *
     * @return The date and time of the event.
     */
    public String getAt() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + this.time;
    }
}
