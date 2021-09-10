package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructs a new Event using the given description, from time and to time.
     *
     * @param description Description of the Event.
     * @param fromDateTime From time of the Event.
     * @param toDateTime To time of the Event.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Constructs a new Event using the given description, done status, from time and to time.
     *
     * @param description Description of the new Event.
     * @param isDone Done status of the new Event.
     * @param fromDateTime From time of the new Event.
     * @param toDateTime To time of the new Event.
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
        return ("E%" + isDone + "%" + description + "%" + fromDateTime + "%" + toDateTime + "\n");
    }

    @Override
    protected LocalDateTime getDateTime() {
        return fromDateTime;
    }
}
