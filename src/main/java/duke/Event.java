package duke;

public class Event extends Task {
    private String eventTime;

    /**
     * Task associated with a given start time
     * @param description description of the event
     * @param eventTime when the event will occur
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[E]%s (at: %s)", stem, this.eventTime);
    }
}
