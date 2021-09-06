package duke;

import java.time.LocalDate;

/**
 * Class for event tasks.
 */
public class Event extends Task {
    /**
     * Constructs an event task.
     *
     * @param description String describing the event task.
     * @param time Time of the event.
     * @throws DukeException Thrown if time could not be parsed.
     */
    public Event(String description, LocalDate time) throws DukeException {
        super(description, time);
        category = TaskType.event;
        assert description != null : "description should not be null";
    }

    /**
     * Gets string representation of event task.
     *
     * @return String describing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTimeString() + ")";
    }
}
