package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task which inherits from Task and contains the dateTime for the event.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Event extends Task {
    private final LocalDate dateTime;

    /**
     * Constructor to initialize a new Event.
     *
     * @param description The description of the event.
     * @param dateTime The date of the event.
     */
    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor to initialize a new Event with status.
     *
     * @param description The description of the event.
     * @param dateTime The date of the event.
     * @param isDone The status of the event.
     */
    public Event(String description, LocalDate dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the task in a desired format.
     *
     * @return The string representation of the task, prefixed with a status icon and events identifier.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                getDescription(),
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns the string representation of this task that is suitable for storage.
     *
     * @return The string representation of the encoded string for storage.
     */
    @Override
    public String encodeTaskForStorage() {
        int encodedIsDone = getIsDone() ? 1 : 0;
        return String.format("E | %d | %s | %s", encodedIsDone, getDescription(), dateTime);
    }
}
