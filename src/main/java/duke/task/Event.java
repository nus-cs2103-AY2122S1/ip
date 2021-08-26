package duke.task;

/**
 * Represents an event occurring at a specified date and time.
 */
public class Event extends TimedTask {
    /**
     * Constructor for an event occurring at a specified date and time.
     *
     * @param description The description of the event.
     * @param time        The time of the event.
     */
    public Event(String description, String time) {
        super(description, 'E', time);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), getDateTimeDesc());
    }
}
