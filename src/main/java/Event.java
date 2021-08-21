/**
 * An event is a task that has a date attached.
 */
public class Event extends Task{
    private final String eventDate;

    /**
     * Creates a new event with the given description on the given eventDate.
     *
     * @param description The description of the event
     * @param eventDate The date of the event
     */
    public Event(String description, String eventDate) {
        super(description, "E");
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", eventDate); // No preposition
    }

    @Override
    public String getDataLine() {
        return super.getDataLine() + " | " + eventDate;
    }
}
