package jarvis.task;

/**
 * Encapsulates the event task which contains a description and event time.
 */
public class Event extends Task {
    private String eventTime;

    /**
     * Constructor for Event.
     *
     * @param description The description for the event.
     * @param eventTime The event time.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * String representation of an event task.
     *
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventTime);
    }

    /**
     * String representation of an event task that is to be saved to storage file.
     *
     * @return String representation that is to be saved.
     */
    @Override
    public String toStorageFormatString() {
        return String.format("%s;;;%s;;;%s", "E", super.toStorageFormatString(), eventTime);
    }
}
