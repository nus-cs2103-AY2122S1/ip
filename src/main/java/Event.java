/**
 * A Task with a date and time.
 * @author Thomas Hogben
 */
public class Event extends Task {
    private String eventDate;

    /**
     * @param description The description of the Event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * @param description The description of the Event.
     * @param eventDate The date and time of the Event.
     */
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        String result = "[E]" + super.toString();
        if (eventDate != null) {
            result += " (at: " + this.eventDate + ")";
        }
        return result;
    }
}
