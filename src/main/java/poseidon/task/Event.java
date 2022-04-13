package poseidon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import poseidon.storage.Storage;

/**
 * Represents an {@code Event} object that contains the description of the event task,
 * a date and time to mark the beginning of the event and
 * a date and time to mark the end of the event.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Event extends Task {

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructs a new {@code Event} using the given description, from time and to time.
     *
     * @param description Description of the {@code Event}.
     * @param fromDateTime From time of the {@code Event}.
     * @param toDateTime To time of the {@code Event}.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Constructs a new {@code Event} using the given description, done status, from time and to time.
     *
     * @param description Description of the new {@code Event}.
     * @param isDone Done status of the new {@code Event}.
     * @param fromDateTime From time of the new {@code Event}.
     * @param toDateTime To time of the new {@code Event}.
     */
    public Event(String description, boolean isDone, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.isDone = isDone;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description
                + " (from: "
                + fromDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
                + " to "
                + toDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
                + ")";
    }

    @Override
    public String toStorage() {
        return ("E" + Storage.STORAGE_SEPARATOR
                + isDone + Storage.STORAGE_SEPARATOR
                + description + Storage.STORAGE_SEPARATOR
                + fromDateTime + Storage.STORAGE_SEPARATOR
                + toDateTime + "\n");
    }

    @Override
    protected LocalDateTime getDateTime() {
        return fromDateTime;
    }
}
