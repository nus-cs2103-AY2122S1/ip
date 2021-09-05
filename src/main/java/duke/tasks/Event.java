package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a start date.
 * The date of the Event is stored as a LocalDate object.
 *
 * @author ruiquan
 */
public class Event extends Task {
    private final LocalDate at;

    /**
     * Constructs an Event given the description and start date.
     *
     * @param description The event description.
     * @param at The start date of this event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event given the description,
     * completion status and start time.
     *
     * @param description The event description.
     * @param isDone Whether the task is completed.
     * @param at The start time of this event.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String format() {
        return String.format("E, %d, %s, %s", isDone ? 1 : 0, description, at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
