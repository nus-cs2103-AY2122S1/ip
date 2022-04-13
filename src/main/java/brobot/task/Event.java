package brobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private final LocalDateTime at;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d,yyyy hh:mma",
            Locale.ENGLISH);


    /**
     * Constructor.
     *
     * @param content Main Content of the event task.
     * @param at      Date of the event.
     */
    public Event(String content, LocalDateTime at) {
        super(content);
        this.at = at;
    }

    /**
     * String representation of an event.
     *
     * @return Event in string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    /**
     * String representation of an Event task for brobot.storage.
     *
     * @return Event task in String(Storage format).
     */
    public String toStorageString() {
        String basicStorageFormat = super.toStorageString();
        return String.format("E %s | %s", basicStorageFormat, at);
    }
}
