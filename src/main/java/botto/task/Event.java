package botto.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Format for the Botto bot's event task.
 */
public class Event extends Task implements Comparable<Event> {
    protected LocalDateTime dateTime;

    /**
     * Constructor for an event.
     *
     * @param description description of the event.
     * @param dateTime datetime of the event.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Return string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.getDefault());
        return "[E]" + super.toString() + " (at: " + formatter.format(this.dateTime) + ")";
    }

    /**
     * Compare the two deadlines.
     *
     * @param o another deadline.
     * @return the value of this deadline compared to other deadline.
     */
    @Override
    public int compareTo(Event o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}
