/**
 * An Event is a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates an Event with the provided description and start and end date/time.
     * @param description The description of the Event task.
     * @param at The date/time of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts the Event into a String that represents the Event.
     * @return The String representation of a Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
