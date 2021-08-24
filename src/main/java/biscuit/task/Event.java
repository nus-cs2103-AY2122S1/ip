package biscuit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class: For tasks that start at a specific time and ends at a specific time.
 * eg. team project meeting on 2/10/2019 2-4pm.
 */
public class Event extends Task {
    private final LocalDateTime date;

    /**
     * Constructs Event class.
     *
     * @param description Task description.
     * @param date        Date task is due.
     */
    public Event(String description, LocalDateTime date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    /**
     * Constructs Event class.
     * Used when need to set isDone.
     *
     * @param isDone      Boolean of if task is done.
     * @param description Task description.
     * @param date        Date task is due.
     */
    public Event(boolean isDone, String description, LocalDateTime date) {
        super(isDone, description, TaskType.EVENT);
        this.date = date;
    }

    /**
     * Gets Task date.
     *
     * @return Task date.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns string representation of Event.
     *
     * @return Event String.
     */
    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + " (at: "
                + date.format(DateTimeFormatter.ofPattern("dd LLL yyyy hh:mm a")) + ")";
    }
}
