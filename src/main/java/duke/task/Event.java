package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An {@code Event} class that extends from {@code Task}
 * and sets a LocalDateTime {@code time}.
 */
public class Event extends Task {
    private final LocalDateTime time;

    /**
     * Initialise constructor for an event.
     *
     * @param taskName Description of the event.
     * @param time Time of the event, indicated with /at {time}
     */
    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Returns a {@code string} representation of an event.
     * @return event name and whether it is completed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

}
