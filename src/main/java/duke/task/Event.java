package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an event - a task that starts at a specific date
 * and ends at a specific date.
 */
public class Event extends Task {
    public static final String IDENTIFIER = "E";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private final LocalDateTime date;

    /**
     * Constructor for an event.
     *
     * @param description Description of the event.
     * @param date        Date of the event.
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Gets the date of the event.
     *
     * @return Date of the event.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets the type identifier of an event.
     *
     * @return String "E" representing an event.
     */
    @Override
    public String getType() {
        return IDENTIFIER;
    }

    /**
     * String representation of an event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date.format(FORMATTER));
    }
}


